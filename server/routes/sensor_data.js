var express = require('express');
var router = express.Router();

/* GET a sensor_data 's listing. */
router.get('/', function(req, res) {
  var  knex = req.app.get('knex');
  knex.select('*').from('sensor_data')
  .then(function(rows) {
   console.log(rows);
   res.render('sensor_data/index', {'rows':rows});
  });
    
  //res.send('respond with a resource');
});

/* create a sensor form. */
router.get('/createform', function(req, res) {
    
   res.render('sensor_data/createform', { title: 'Express' });
    
});

/* read a sensor. */
router.get('/:id', function(req, res) {
  
  var  knex = req.app.get('knex');
  knex('sensor_data').where('id', req.params.id)
  .then(function(rows) {
   console.log(rows);
   res.render('sensor_data/detail', { row: rows[0] });
  });
    
    
    //console.log(req.params.id);
  //res.send('respond with a resource'+req.params.id);
});


/* read a sensor. */
router.get('/api/:id', function(req, res) {
  var  knex = req.app.get('knex');
  knex('sensor_data').where('id', req.params.id)
  .then(function(rows) {
   console.log(rows);
   sensordata = JSON.parse(rows[0]['data']);
   res.json(sensordata)
   //res.render('sensor_data/detail', { row: rows[0] });
  });
});


/* read a sensor. */
router.get('/:id/editform', function(req, res) {
  
  var  knex = req.app.get('knex');
  knex('sensor_data').where('id', req.params.id)
  .then(function(rows) {
   console.log(rows);
   res.render('sensor_data/editform', { title: 'Express' });
  });
    
    //console.log(req.params.id);
  //res.send('respond with a resource'+req.params.id);
});

/* update a sensor. */
router.put('/:id', function(req, res) {
    var  knex = req.app.get('knex');
   knex('sensor_data')
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



/* create a sensor. */
//test:curl --data "name=curltestxx&category=1" http://localhost:3000/sensor_data

router.post('/', function(req, res) {
    console.log(req.param('name'));
    console.log(req.body);
    var  knex = req.app.get('knex');
      knex('sensor_data')
     .insert(req.body)
 // .insert([{name: 'pir'},{name: 'camera'}])
     .then(function(ret){
        console.log(ret);
        res.redirect('/sensor_data');
    });
    //console.log(req);
  //res.send('respond with a resource'+req.params.id);
});



/* create a sensor. */
//test:curl --data "name=curltestxx&category=1" http://localhost:3000/sensor_data

router.post('/api', function(req, res) {
    console.log(req.param('name'));
    console.log(req.body);
    var  knex = req.app.get('knex');
      knex('sensor_data')
     .insert(req.body)
 // .insert([{name: 'pir'},{name: 'camera'}])
     .then(function(ret){
        console.log(ret);
        //res.redirect('/sensor_data');
        res.send(ret);
    });
    //console.log(req);
  //res.send('respond with a resource'+req.params.id);
});




/* delete a sensor. */
router.delete('/', function(req, res) {
   console.log(req);
  res.send('respond with a resource'+req.params.id);
});

module.exports = router;
