package com.kokoo.mongo

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document

class CarService(
    private val database: MongoDatabase
) {
    var collection: MongoCollection<Document>

    init {
        database.createCollection("cars")
        collection = database.getCollection("cars")
    }

    suspend fun create(car: Car): String = withContext(Dispatchers.IO) {
        val doc = car.toDocument()
        collection.insertOne(doc)
        doc["_id"].toString()
    }

    suspend fun getAll(): List<Car> = withContext(Dispatchers.IO) {
        collection.find()
            .asSequence()
            .map { Car.fromDocument(it) }
            .toList()
    }
}