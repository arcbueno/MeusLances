const mongoose = require('mongoose');
const AutoIncrement = require('mongoose-sequence')(mongoose);
const Schema = mongoose.Schema;

const schema = new Schema({
    id: {
        type: Number,
        required: false,
    },
    name: {
        type: String,
        required: true,
        trim: true
    },
    description: {
        type: String,
        required: true
    },
}, { _id: false });
// schema.plugin(AutoIncrement);

module.exports = mongoose.model('Label', schema);