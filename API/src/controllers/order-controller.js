const { validationResult } = require('express-validator');
const mongoose = require('mongoose');
const Order = mongoose.model('Order');

// list
exports.listOrder = async (req, res) => {
    try {
        const data = await Order.find({}, 'code productIdList totalPrice discountCode finalPrice');
        res.status(200).send(data);
    } catch (e) {
        res.status(500).send({ message: 'Error when access Orders' });
    }
};

// create
exports.createOrder = async (req, res) => {
    const { errors } = validationResult(req);

    if (errors.length > 0) {
        return res.status(400).send({ message: errors })
    }

    try {

        const order = new Order({
            code: req.body.code,
            productIdList: req.body.productIdList,
            totalPrice: req.body.totalPrice,
            discountCode: req.body.discountCode,
            finalPrice: req.body.finalPrice,
        });

        console.log(order)

        await order.save();

        res.status(201).send({ message: 'Order successfully created', data: order });
    } catch (e) {
        res.status(500).send({ message: 'Error when creating a new Order' });
        console.log(e);
    }
};

// update
exports.updateOrder = async (req, res) => {
    try {
        const filter = { _id: req.body._id }

        const data = await Order.findOneAndUpdate(filter, {
            code: req.body.code,
            productIdList: req.body.productIdList,
            totalPrice: req.body.totalPrice,
            discountCode: req.body.discountCode,
            finalPrice: req.body.finalPrice,
        });

        await data.save();
        res.status(201).send({ message: 'Order successfully updated', data: data });
    }
    catch (e) {
        res.status(500).send({ message: 'Error when updating a Order' });
        console.log(e);
    }
}

exports.deleteOrder = async (req, res) => {
    try {
        await Order.findByIdAndRemove(req.params.id)
        await Order.save();
        res.status(201).send({ message: 'Order successfully deleted' });
    } catch (e) {
        res.status(201).send({ message: 'Order successfully deleted' });
        console.log(e);
    }
}