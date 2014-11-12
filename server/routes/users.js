var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res) {
    
    
      //test db crud
    
    var  knex = req.app.get('knex');
    knex.select('*').from('devices')
  .then(function(rows) {
   console.log(rows)
  });
    
    knex('sensors')
     .insert({name: 'pir'})     .insert({name: 'pir'})

 // .insert([{name: 'pir'},{name: 'camera'}])
    .then(function(ret){
    console.log(ret)
    });
    
    knex('sensors')
  .where('name', '=', 'pir')
  .update({
    note: 'updated'
  })
    .then(function(ret){
    console.log(ret)
    });
    
    knex('sensors')
  .where('name', '=', 'pir')
  .del()
    .then(function(ret){
    console.log(ret)
    });
    

    
    
  res.send('respond with a resource');
});

module.exports = router;
