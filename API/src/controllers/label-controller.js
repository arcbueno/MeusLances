const { validationResult } = require('express-validator');
const mongoose = require('mongoose');
const Label = mongoose.model('Label');

// list
exports.listLabel = async (req, res) => {
    try {
        const data = await Label.find({}, 'id name description labelId price');
        res.status(200).send(data);
    } catch (e) {
        res.status(500).send({ message: 'Error when access Labels' });
    }
};

// create
exports.createLabel = async (req, res) => {
    const { errors } = validationResult(req);

    if (errors.length > 0) {
        return res.status(400).send({ message: errors })
    }

    try {
        const data = await Label.find({ name: req.body.name })
        if (data.length > 0) {
            res.status(422).send({ message: 'Label already created' });
            return;
        }

        const product = new Label({
            name: req.body.name,
            description: req.body.description,
        });

        console.log(product)

        await product.save();

        res.status(201).send({ message: 'Label successfully created', data: product });
    } catch (e) {
        res.status(500).send({ message: 'Error when creating a new Label' });
        console.log(e);
    }
};

// update
exports.updateLabel = async (req, res) => {
    try {

        const check = await Label.find({ name: req.body.name })
        if (check.length > 0) {
            res.status(422).send({ message: 'Label already created' });
            return;
        }

        const filter = { _id: req.body._id }

        const data = await Label.findOneAndUpdate(filter, {
            name: req.body.name,
            description: req.body.description,
        });

        await data.save();
        res.status(201).send({ message: 'Label successfully updated', data: data });
    }
    catch (e) {
        res.status(500).send({ message: 'Error when updating a label' });
        console.log(e);
    }
}

exports.deleteLabel = async (req, res) => {
    try {
        await Label.findByIdAndRemove(req.params.id)
        await Label.save();
        res.status(201).send({ message: 'Label successfully deleted' });
    } catch (e) {
        res.status(201).send({ message: 'Label successfully deleted' });
        console.log(e);
    }
}