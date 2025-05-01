-- Categorías
INSERT INTO Category (id, name) VALUES (0, 'Undefined');
INSERT INTO Category (id, name) VALUES (1, 'DC');
INSERT INTO Category (id, name) VALUES (2, 'Marvel');
ALTER TABLE Category ALTER COLUMN id RESTART WITH 3;


INSERT INTO Comic (id, name, syn, price, sales, pages, review, dat, url, category_id) VALUES (NEXT VALUE FOR comic_seq,'Biblioteca Marvel Omnibus. La Imposible Patrulla-X 3','La mítica Patrulla-X como nunca antes. Desde "La saga de El Nido" hasta "Dios ama, el hombre mata". Incluye la miniserie de Lobezno, el debut de Pícara, Los Morlocks y Fénix Oscura. Imprescindible.',33.27,705,100,2.36,'2025-04-05','https://www.panini.es/media/catalog/product/cache/2d16730310b7945c46ddd1ca513e3c42/s/o/somni046_0.jpg' ,2);
INSERT INTO Comic (id, name, syn, price, sales, pages, review, dat, url, category_id) VALUES (NEXT VALUE FOR comic_seq,'Superman Absolute','El héroe más brillante del Universo DC, sin fortaleza, sin hogar… El Hombre de Acero en su versión Absolute. Aislado, entenderás cuán vitales son el entorno y la comunidad para los héroes.',54.03,459,100,2.86,'2024-04-27','https://www.panini.es/media/catalog/product/cache/2d16730310b7945c46ddd1ca513e3c42/s/a/sabsu001_0.jpg' ,1);