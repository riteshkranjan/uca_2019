const express = require('express')
const app = express()
var os = require("os");

app.use(express.json())

app.get('/', (req,res) => {
   console.log("get request");
   var hostname = os.hostname();
   res.send("hello from "+hostname+" \n");
})

const PORT=8083; 

module.exports = app.listen(PORT, () => console.log('server started at port '+PORT))
