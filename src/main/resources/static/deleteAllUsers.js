function deleteAllUsers() {
  var txt;
 
  var r = confirm("Czy na pewno chcesz usunąć wszystkich użytkowników?");
  
  
  if (r == true) {
    txt = "Użytkownicy skasowani";
  } else {
    txt = "Użytkownicy nie zostali skasowani";
  }
  document.getElementById("demo").innerHTML = txt;
}