package com.easydevs.purchase.service;

import com.easydevs.purchase.model.PurchaseInvoice;
import com.easydevs.support.DbIdSequence;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ibm on 2017-01-18.
 */
@Service
public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService {


    @Autowired
    @Qualifier("contentMongoTemplate")
    private MongoTemplate mongoTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final String INVOICE_ID_SEQUENCE_COLLECTION_NAME = "invoiceIdSequence";

    @Override
    public PurchaseInvoice getPurchaseInvoiceById(Long id) {
        log.info("PurchaseInvoiceService - getPurchaseInvoiceById", id);

        Query query = new Query(Criteria.where("id").is(id));
        List<PurchaseInvoice> invoiceList = mongoTemplate.find(query, PurchaseInvoice.class);
        if (invoiceList.size() == 1) {
            return invoiceList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public PurchaseInvoice createNewPurchaseInvoice() {

        return new PurchaseInvoice(getNewIdAndInc());

    }

    @Override
    public List<PurchaseInvoice> getPurchaseInvoiceListByUserId(long userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return  mongoTemplate.find(query, PurchaseInvoice.class);
    }

    private Long getNewIdAndInc() {
        log.info("PurchaseInvoiceService - getNewIdAndInc");

        List<DbIdSequence> userIdSequenceList = mongoTemplate.find(new Query(), DbIdSequence.class, INVOICE_ID_SEQUENCE_COLLECTION_NAME);

        DbIdSequence userIdSequence;
        if (!userIdSequenceList.isEmpty()) {
            userIdSequence = userIdSequenceList.get(0);
        } else {
            userIdSequence = new DbIdSequence();
        }

        Long currentId = userIdSequence.getCurrent();
        userIdSequence.setCurrent(++currentId);

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(userIdSequence, dbDoc);
        Update update = Update.fromDBObject(dbDoc);
        mongoTemplate.upsert(new Query(), update, DbIdSequence.class, INVOICE_ID_SEQUENCE_COLLECTION_NAME);

        return currentId;
    }

    @Override
    public void updatePurchaseInvoice(PurchaseInvoice invoice) {
        log.info("PurchaseInvoiceService - updatePurchaseInvoice", invoice.toString());

        Query query = new Query(Criteria.where("id").is(invoice.getId()));

        DBObject dbDoc = new BasicDBObject();
        mongoTemplate.getConverter().write(invoice, dbDoc);
        Update update = Update.fromDBObject(dbDoc);

        mongoTemplate.upsert(query, update, PurchaseInvoice.class);
    }

    @Override
    public void removePurchaseInvoice(PurchaseInvoice invoice) {
        log.info("PurchaseInvoiceService - removePurchaseInvoice", invoice.getId());

        Query query = new Query(Criteria.where("id").is(invoice.getId()));
        mongoTemplate.findAndRemove(query, PurchaseInvoice.class);
    }
}
