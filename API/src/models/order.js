const mongoose = require('mongoose');
const AutoIncrement = require('mongoose-sequence')(mongoose);
const Schema = mongoose.Schema;

const schema = new Schema({
    code: {
        type: String,
        required: true,
        trim: true
    },
    productIdList: [{
        type: Number,
        required: true,
    }],
    totalPrice: {
        type: Number,
        required: true,
    },
    discountCode: {
        type: String,
        required: false,
    },
    finalPrice: {
        type: Number,
        required: true,
    },
});
// schema.plugin(AutoIncrement);

module.exports = mongoose.model('Order', schema);