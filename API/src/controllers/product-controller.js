const { validationResult } = require('express-validator');
const mongoose = require('mongoose');
const Product = mongoose.model('Product');

// list
exports.listProduct = async (req, res) => {
    try {
        const data = await Product.find({}, 'id name description labelId price');
        res.status(200).send(data);
    } catch (e) {
        res.status(500).send({ message: 'Error when access Products' });
    }
};

// create
exports.createProduct = async (req, res) => {
    const { errors } = validationResult(req);

    if (errors.length > 0) {
        return res.status(400).send({ message: errors })
    }

    try {
        const data = await Product.find({ name: req.body.name })
        if (data.length > 0) {
            res.status(422).send({ message: 'Product already created' });
            return;
        }

        const product = new Product({
            name: req.body.name,
            description: req.body.description,
            labelId: req.body.labelId,
            price: req.body.price,
        });

        console.log(product)

        await product.save();

        res.status(201).send({ message: 'Product successfully created', data: product });
    } catch (e) {
        res.status(500).send({ message: 'Error when creating a new product' });
        console.log(e);
    }
};

// update
exports.updateProduct = async (req, res) => {
    try {
        const check = await Product.find({ name: req.body.name })
        if (check.length > 0) {
            res.status(422).send({ message: 'Product already created' });
            return;
        }

        const filter = { _id: req.body._id }

        const data = await Product.findOneAndUpdate(filter, {
            name: req.body.name,
            description: req.body.description,
            labelId: req.body.labelId,
            price: req.body.price,
        });

        await data.save();
        res.status(201).send({ message: 'Product successfully updated', data: data });
    }
    catch (e) {
        res.status(500).send({ message: 'Error when updating a product' });
        console.log(e);
    }
}

exports.deleteProduct = async (req, res) => {
    try {
        await Product.findByIdAndRemove(req.params.id)
        await Product.save();
        res.status(201).send({ message: 'Product successfully deleted' });
    } catch (e) {
        res.status(201).send({ message: 'Product successfully deleted' });
        console.log(e);
    }
}