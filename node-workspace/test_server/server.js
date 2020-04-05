const express = require('express')
const app = express()
var os = require("os");

app.use(express.json())

app.get('/', (req,res) => {
   var ip = req.connection.remoteAddress;
   console.log("get request");
   var hostname = os.hostname();
   res.setHeader('Content-Type', 'application/json');
   res.end(JSON.stringify({ user_ip: ip, server_hostname: hostname, message: "Hello world!!" }));
})

const PORT=8080; 

module.exports = app.listen(PORT, () => console.log('server started at port '+PORT))
