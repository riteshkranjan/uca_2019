const mongoose = require('mongoose')

const schema = new mongoose.Schema({
  name: { type: String, required: true },
  email: { type: String, required: true, max: 255 },
  gpa: { type: Number, required: false },
  modifiedDate: { type: Date, required: true, default: Date.now }
})

module.exports = mongoose.model('Student', schema)