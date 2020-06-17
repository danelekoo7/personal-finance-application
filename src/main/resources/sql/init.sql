# insert demo income
INSERT INTO income
VALUES (null, '3700 zł brutto', 'Wynagrodzenie pani Barbary', 2644, 19);
INSERT INTO income
VALUES (null, '2000 zł brutto (kwota zmienna, otrzymywana nie częściej niż raz na kwartał)', 'Premia pani Barbary',
        1459, 19);
INSERT INTO income
VALUES (null, '2900 zł brutto', 'Wynagrodzenie pana Jacka', 2087, 19);
INSERT INTO income
VALUES (null, '', 'Przelew z firmy kolegi za prowadzenie księgowości', 350, 19);
INSERT INTO income
VALUES (null, '', 'Odsetki od lokat', 12, 19);


# insert demo expense group 1
INSERT INTO expense
VALUES (null, '', 1, 'Jedzenie i picie', 1700, 0, 19);
INSERT INTO expense
VALUES (null, '', 1, 'Koszty leczenia i leki', 35, 0, 19);
INSERT INTO expense
VALUES (null, '', 1, 'Ubranie', 500, 0, 19);
INSERT INTO expense
VALUES (null, '', 1, 'Czynsz za mieszkanie (plus woda i ogrzewanie)', 460, 0, 19);
INSERT INTO expense
VALUES (null, '', 1, 'Opłaty za energię elektryczną', 90, 0, 19);
INSERT INTO expense
VALUES (null, '', 1, 'Koszty dojazdu do pracy', 180, 0, 19);
INSERT INTO expense
VALUES (null, '', 1, 'Higiena, kosmetyki, fryzjer', 220, 0, 19);
INSERT INTO expense
VALUES (null, '', 1, 'Środki czystości, pralnia chemiczna', 120, 0, 19);
INSERT INTO expense
VALUES (null, '', 1, 'Wydatki na szkołę
i przedszkole dzieci', 550, 0, 19);
INSERT INTO expense
VALUES (null, '', 1, 'Rachunek telefoniczny i opłata za internet', 95, 0, 19);
INSERT INTO expense
VALUES (null, '', 1, 'Inne niezbędne
potrzeby', 0, 0, 19);

# insert demo expense group 2
INSERT INTO expense
VALUES (null, '', 2, 'Rata kredytu
hipotecznego', 1300, 0, 19);
INSERT INTO expense
VALUES (null, '', 2, 'Bieżące raty kredytów i pożyczek konsumenckich, spłata kart kredytowych', 150, 0, 19);
INSERT INTO expense
VALUES (null, 'Płatne raz w roku, ale w każdym miesiącu odkładamy 1/12 rocznej kwoty.', 2,
        'Składka na ubezpieczenie OC', 45, 0, 19);
INSERT INTO expense
VALUES (null, 'Płatne raz w roku, ale w każdym miesiącu odkładamy 1/12 rocznej kwoty.', 2,
        'Składka na ubezpieczenie mieszkania', 65, 0, 19);
INSERT INTO expense
VALUES (null, 'Płatne raz w roku, ale w każdym miesiącu odkładamy 1/12 rocznej kwoty.', 2,
        'Składka na ubezpieczenie na życie (dwie polisy terminowego ubezpieczenia na życie)', 160, 0, 19);
INSERT INTO expense
VALUES (null, 'Płatne raz w roku, ale w każdym miesiącu odkładamy 1/12 rocznej kwoty.', 2,
        'Inne składki ubezpieczeniowe', 0, 0, 19);
INSERT INTO expense
VALUES (null, 'Płatne raz w roku, ale w każdym miesiącu odkładamy 1/12 rocznej kwoty.', 2,
        'Podatek od nieruchomości i/lub opłata za użytkowanie wieczyste', 45, 0, 19);
INSERT INTO expense
VALUES (null, '', 2, 'Inne raty, składki i podatki', 0, 0, 19);


# insert demo expense group 3
INSERT INTO expense
VALUES (null, 'Tyle wydamy na drobne przyjemności dla siebie.', 3, 'Kwota na drobne przyjemnośc', 300, 0, 19);
INSERT INTO expense
VALUES (null, '2000 zł już odłożone.', 3, 'Bufor 2000 zł', 0, 0, 19);
INSERT INTO expense
VALUES (null, 'Taką kwotę przeznaczamy na wcześniejszą spłatę długów.', 3, 'Szybsza spłata długów', 537, 0, 19);
INSERT INTO expense
VALUES (null, 'Patrz krok 5: Zbuduj fundusz bezpieczeństwa.', 3, 'Wpłata na fundusz bezpieczeństwa', 0, 0, 19);
INSERT INTO expense
VALUES (null, '', 3, 'Zabawki dla dzieci', 0, 0, 19);
INSERT INTO expense
VALUES (null, '', 3, 'Własny rozwój (np. języki obce, inne kursy) i dodatkowe zajęcia dla dzieci', 0, 0, 19);
INSERT INTO expense
VALUES (null, '', 3, 'Rezerwa na przegląd techniczny samochodu, olej, wymianę opon', 0, 0, 19);
INSERT INTO expense
VALUES (null, '', 3, 'Hobby, kino, teatr itp.', 0, 0, 19);
INSERT INTO expense
VALUES (null, 'Patrz krok 7: Otwórz konto emerytalne.', 3, 'Wpłata na konto emerytalne', 0, 0, 19);
INSERT INTO expense
VALUES (null, 'W tej pozycji również odkładamy stosowną porcję właściwej kwoty, którą przeznaczymy na ten cel.', 3,
        'Rezerwa na święta (prezenty, organizacja)', 0, 0, 19);
INSERT INTO expense
VALUES (null, 'W tej pozycji również odkładamy stosowną porcję właściwej kwoty, którą przeznaczymy na ten cel.', 3,
        'Rezerwa na wakacje i urlopy (wraz z ubezpieczeniem kosztów leczenia)', 0, 0, 19);
INSERT INTO expense
VALUES (null, 'Patrz krok 8: Przygotuj środki na studia dzieci.', 3, 'Wpłata na przyszłe studia dzieci', 0, 0, 19);
INSERT INTO expense
VALUES (null, '', 3, 'Inne ważne wydatki', 0, 0, 19);


# insert demo expense group 4
INSERT INTO expense VALUES (null, 'Patrz krok 9: Zaatakuj największy dług.', 4, 'Rezerwa na dodatkową ratę kredytu hipotecznego', 0, 0, 19);
INSERT INTO expense VALUES (null, 'Patrz krok 10: Przeznacz nadwyżki na trzy ważne cele.', 4, 'Rozrywka i przyjemności', 0, 0, 19);
INSERT INTO expense VALUES (null, 'Patrz krok 10: Przeznacz nadwyżki na trzy ważne cele.', 4, 'Lokowanie nadwyżek', 0, 0, 19);
INSERT INTO expense VALUES (null, 'Patrz krok 10: Przeznacz nadwyżki na trzy ważne cele.', 4, 'Pomoc innym', 0, 0, 19);
INSERT INTO expense VALUES (null, '', 4, 'Dowolne zakupy', 0, 0, 19);
