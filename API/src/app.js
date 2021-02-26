const express = require('express');
const mongoose = require('mongoose');
const { check } = require('express-validator');
require('dotenv').config();

// App
const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

mongoose.connect(process.env.MONGODB_URI || "mongodb+srv://arcbueno:%2AJava1334@meuslanches.b8wrl.mongodb.net/meusLanches?retryWrites=true&w=majority", {
    useUnifiedTopology: true,
    useNewUrlParser: true
});

const db = mongoose.connection;

db.on('connected', () => {
    console.log('Mongoose Meus Lanches connection is open');
});

db.on('error', err => {
    console.log(`Mongoose default connection has occured \n${err}`);
});

db.on('disconnected', () => {
    console.log('Mongoose default connection is disconnected');
});

process.on('SIGINT', () => {
    db.close(() => {
        console.log(
            'Mongoose default connection is disconnected due to application termination'
        );
        process.exit(0);
    });
});

// Load models
const Product = require('./models/product');
const Label = require('./models/label');
const Order = require('./models/order');

// Load routes
const indexRoutes = require('../routes/index-routes');
app.use('/', indexRoutes);

const productsRoutes = require('../routes/product-routes');
app.use('/products', productsRoutes);

const labelsRoutes = require('../routes/label-routes');
app.use('/labels', labelsRoutes);

const ordersRoutes = require('../routes/order-routes');
app.use('/orders', ordersRoutes);

module.exports = app;