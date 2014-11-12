var express = require('express');
var router = express.Router();

/* GET a sensors 's sensors listing. */
router.get('/', function(req, res) {
  var  knex = req.app.get('knex');
  knex.select('*').from('sensors')
  .then(function(rows) {
   console.log(rows);
   res.render('sensors/index', {'rows':rows});
  });
    
  //res.send('respond with a resource');
});

/* create a sensor form. */
router.get('/createform', function(req, res) {
    
   res.render('sensors/createform', { title: 'Express' });
    
});

/* read a sensor. */
router.get('/:id', function(req, res) {
  
  var  knex = req.app.get('knex');
  knex('sensors').where('id', req.params.id)
  .then(function(rows) {
   console.log(rows);
   res.render('sensors/detail', { row: rows[0] });
  });
    
    
    //console.log(req.params.id);
  //res.send('respond with a resource'+req.params.id);
});

/* read a sensor. */
router.get('/:id/editform', function(req, res) {
  
  var  knex = req.app.get('knex');
  knex('sensors').where('id', req.params.id)
  .then(function(rows) {
   console.log(rows);
   res.render('sensors/editform', { title: 'Express' });
  });
    
    //console.log(req.params.id);
  //res.send('respond with a resource'+req.params.id);
});

/* update a sensor. */
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



/* create a sensor. */
//test:curl --data "name=curltestxx&category=1" http://localhost:3000/sensors

router.post('/', function(req, res) {
    console.log(req.param('name'));
    console.log(req.body);
    var  knex = req.app.get('knex');
      knex('sensors')
     .insert(req.body)
 // .insert([{name: 'pir'},{name: 'camera'}])
     .then(function(ret){
        console.log(ret);
        res.redirect('/sensors');
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
