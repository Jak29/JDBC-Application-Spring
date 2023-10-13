INSERT INTO salons (salon_id, salon_name, salon_address, salon_phone, salon_days)
VALUES
    (1,'Salon Jak', 'A12B345', 0831234567, 1111110),
    (2,'Salon Jen', 'C12D345', 0837654321, 1111101),
    (3,'Salon Alex', 'E12F345', 0830000000, 1111100);

INSERT INTO stylists (stylist_id, stylist_name, stylist_phone, stylist_salary, salon_id)
VALUES
    (1, "Jak", 12345678, 10000, 1),
    (2, "Jen", 87654321, 14000, 2),
    (3, "Alex", 00223344, 12500, 3),
    (4, "Owen", 12312300, 9000, 4);
