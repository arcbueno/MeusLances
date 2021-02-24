const mongoose = require('mongoose');
const AutoIncrement = require('mongoose-sequence')(mongoose);
const Schema = mongoose.Schema;

const schema = new Schema({
    name: {
        type: String,
        required: true,
        trim: true
    },
    description: {
        type: String,
        required: true
    },
    labelId: {
        type: Number,
        required: false
    },
    price: {
        type: Number,
        required: true
    }
}, { _id: true });

module.exports = mongoose.model('Product', schema);