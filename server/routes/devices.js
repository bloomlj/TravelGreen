var express = require('express');
var router = express.Router();

/* GET devices listing. */
router.get('/', function(req, res) {
  var  knex = req.app.get('knex');
  knex.select('*').from('devices')
  .then(function(rows) {
   console.log(rows);
   res.render('devices/index', {'rows':rows});
  });
    
  //res.send('respond with a resource');
});

/* create a device form. */
router.get('/createform', function(req, res) {
    
   res.render('devices/createform', { title: 'Express' });
    
});

/* read a device. */
router.get('/:id', function(req, res) {
  
  var  knex = req.app.get('knex');
  knex('devices').where('id', req.params.id)
  .then(function(rows) {
   console.log(rows);
   res.render('devices/detail', { row: rows[0] });
  });
    
    
    //console.log(req.params.id);
  //res.send('respond with a resource'+req.params.id);
});

/* read a device. */
router.get('/:id/editform', function(req, res) {
  
  var  knex = req.app.get('knex');
  knex('devices').where('id', req.params.id)
  .then(function(rows) {
   console.log(rows);
   res.render('devices/editform', { title: 'Express' });
  });
    
    //console.log(req.params.id);
  //res.send('respond with a resource'+req.params.id);
});

/* update a device. */
router.put('/:id', function(req, res) {
    var  knex = req.app.get('knex');
   knex('sensors')
  .where('name', '=', 'pir')
  .update({
    note: 'updated'
  })
    .then(function(ret){
    console.log(ret)
    });
    
    console.log(req.params.id);
  res.send('respond with a resource'+req.params.id);
});



/* create a device. */
//test:curl --data "name=curltestxx&category=1" http://localhost:3000/devices

router.post('/', function(req, res) {
    console.log(req.param('name'));
    console.log(req.body);
    var  knex = req.app.get('knex');
      knex('devices')
     .insert(req.body)
 // .insert([{name: 'pir'},{name: 'camera'}])
     .then(function(ret){
        console.log(ret);
        res.redirect('/devices');
    });
    //console.log(req);
  //res.send('respond with a resource'+req.params.id);
});

/* delete a device. */
router.delete('/', function(req, res) {
   console.log(req);
  res.send('respond with a resource'+req.params.id);
});

module.exports = router;
