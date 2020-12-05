# GOV APP
Aplikacja do zarządzania pracą organów samorządu terytorialnego.

1. Technologie:

Spring boot 2.3.5
Spring data
Spring Security
Hibernate
MySQl
Thymeleaf

Aplikacja:
1. Poziomy dostępu:

- Niezalogowany Użytkownik:
    * Przeglądanie profili radnych;
    * Przeglądanie złożonych przez radnych interpelacji, udziwlonych odpowiedzi + możliwość pobrania ich w wersji pdf.
    * Przeglądanie utworzonych porządków obrad;
    
- Zalogowany Użytkownik (Rola Radny) - dodatkowo:
    * Tworzenie interplacji (zatrzeżone tylko dla Radnego);
    * Usuwanie swoich interplacji (zastrzeżone tylko radnego);

- Zaogogowany Użytkownik (Rola Admin) - dodatkowo:
    * Tworzenie porządku obrad (możliwość dodania pdf z projektem uchwały);
    * Tworzenie, modyfikowanie, usuwanie (wraz z dodanymi wszystkimi interpelacjami radnego), aktywacja i deaktywacja profilu Radnego;
    * Udzielanie odpowiedzi na złożone interplacje;      
        
        
Status aplikacji:
- w przygotowaniu;
