# WEB car rental service
Project has been developed with partial usage of pair programming technique and is implementing full CRUD functionality for registering and login customers with separate admin privileges and menus. Customers are allowed to see rental offer of 20 exemplary cars (pages with photos and car specification). Customer can rent chosen vehicle for given period if they have sufficient funds or return the car. Admin accepts checkout of transaction. He also manages customers’ accounts and transactions.

# SalonSamochodowy
Projekt WEB wypożyczalni samochodów

Twórcy:
Konrad Żołyński
https://github.com/Konrad-code
konrad.zolynski@gmail.com
+48 533 683 168

Janek Misiórski
https://github.com/janekjmf
janekjmf@gmail.com
+48 883 483 807

Wypożyczalnia Samochodów to strona internetowa wyimaginowanej wypożyczalni aut.
Projekt został zrealizowany przy użyciu Springa i języków: Java, HTML, SQL, CSS, JavaScript.

Aby uruchomić portal należy go zaimportować jako plik Maven oraz mieć zainstalowane środowisko pgAdmin 4.
W pliku „sql\SalonSamochodowy_properties.txt” trzeba zmienić pozycje „login” oraz „password” na te, które są podane w programie pgAdmin 4. 
Następnie przekopiowaną zawartość pliku „sql\build” należy uruchomić jako skrypt sql w przeznaczonej na testy aplikacji bazie danych.
Po zaimportowaniu i uruchomieniu aplikacji można wejść za pośrednictwem przeglądarki na "http://localhost:8080" 
(ostatni element ścieżki to nazwa bazy jaka się utworzyło. Zalecamy taka jak jest, ale jak ktoś zrobił inna to musi podmienić)

Jeśli IDE z którego korzysta użytkownik nie dokonuje autolinkowania, to należy plik postgresql-42.2.11.jar z 
folderu źródłowego src kliknąć prawym przyciskiem myszy i dodać do "Build path", po czym plik w drzewku powinien przenieść się do "Referenced libraries".
Jako projekt Maven należy na początku uruchomić go z opcja run as -> clean, następnie tak samo tylko z opcja run as -> install. 
Użytkownik chcący dokonać testów może wykonać uruchomienie run as -> test.
Projekt byl pisany z przeznaczeniem dla uruchomienia w javie 1.8 wiec zaleca się kliknąć PPM na "JRE System Library" 
a następnie "Properties" i zmienić "Execution environment" na JaveSE-1.8"
