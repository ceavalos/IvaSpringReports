$(document).ready(function(){
  


 $('.eBtn').on('click', function(event){
 
   alert("Hola...!!!");
   
   var href = $("#btnAdd").attr('href');
   
   $.get(href, function(data, status){
     
   });
 
 })

});