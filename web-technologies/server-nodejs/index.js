const express = require('express')
const app = express()
const cors = require('cors')
const studentService = require('./routes/studentService')
const mongoose = require('mongoose')
mongoose.connect('mongodb+srv://sarvajana:abcd1234@cluster0-27dec.gcp.mongodb.net/uca?retryWrites=true&w=majority', { useNewUrlParser: true, useUnifiedTopology: true }, function (err, db) {
    if (db != null) {
        console.log("DB connected")
    }
    if (err)
        return console.error(err);
});

app.use(cors())
app.use(express.json())
app.use('/student', studentService)
app.get('/', (req, res) => res.send('Hello World!'))


module.exports = app.listen(3001, () => console.log('server started at port 3001'))
