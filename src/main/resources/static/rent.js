function rent()
{
  var txt;
  var r = confirm("Czy na pewno chcesz wypożyczyć to auto?");
  
  
  if (r == true) 
  {
    txt = "Wypożyczyłeś auto";
  }
  else 
  {
    txt = "Nie wypożyczyłeś auta";
  }
  document.getElementById("demo").innerHTML = txt;
}