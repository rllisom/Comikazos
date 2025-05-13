
INSERT INTO Category (id, name) VALUES (0, 'Undefined');
INSERT INTO Category (id, name) VALUES (1, 'DC');
INSERT INTO Category (id, name) VALUES (2, 'Marvel');
ALTER TABLE Category ALTER COLUMN id RESTART WITH 3;


INSERT INTO Comic (id, name, syn, price, sales, pages, review, dat, url, text_img, category_id) VALUES (NEXT VALUE FOR comic_seq,'Biblioteca Marvel Omnibus. La Imposible Patrulla-X 3','La mítica Patrulla-X como nunca antes. Desde "La saga de El Nido" hasta "Dios ama, el hombre mata". Incluye la miniserie de Lobezno, el debut de Pícara, Los Morlocks y Fénix Oscura. Imprescindible.',33.27,705,100,2.36,'2025-04-05','https://www.panini.es/media/catalog/product/cache/2d16730310b7945c46ddd1ca513e3c42/s/o/somni046_0.jpg','Portada del volumen 3 de la Patrulla X' ,2);
INSERT INTO Comic (id, name, syn, price, sales, pages, review, dat, url, text_img, category_id) VALUES (NEXT VALUE FOR comic_seq,'Superman Absolute','El héroe más brillante del Universo DC, sin fortaleza, sin hogar… El Hombre de Acero en su versión Absolute. Aislado, entenderás cuán vitales son el entorno y la comunidad para los héroes.',54.03,459,100,2.86,'2024-04-27','https://www.panini.es/media/catalog/product/cache/2d16730310b7945c46ddd1ca513e3c42/s/a/sabsu001_0.jpg', 'Portada de Superman Absolute' ,1);
INSERT INTO Comic (id, name, syn, price, sales, pages, review, dat, url, text_img, category_id) VALUES (NEXT VALUE FOR comic_seq,'Batman: Caballero Blanco presenta - Capucha Roja','¡Jason Todd es uno de los buenos! El antiguo Robin se ha reinsertado y está haciendo justicia en un vecindario de Gotham City. Es el momento de dar forma a su propio equipo, empezando por el Chico Maravilla definitivo. ¿Estará a la altura su candidato?',3.23,29,100,3.62,'2025-04-22','https://content.eccediciones.com//productos/19693/Batman_MasAllaDelCaballeroBlanco_CapuchaRoja_2_1acubierta_DEF.jpg', 'Portada del caballero oscura, capucha roja' ,1);




INSERT INTO News (id, date_new, img_new, text_img, title, description) VALUES (1, '2025-05-12', 'https://i0.wp.com/www.tomosygrapas.com/wp-content/uploads/2025/05/Banner-Pacificador.jpg?resize=1024%2C576&ssl=1', 'Noticia sobre el primer trailer de la serie del pacificador', 'Vuelve Pacificador con el primer tráiler de su segunda temporada', 'La plataforma de streaming Max lanza el primer avance del regreso de Pacificador con el teaser tráiler de la segunda temporada de su serie.');
INSERT INTO News (id, date_new, img_new, text_img, title, description) VALUES (2, '2025-05-06', 'https://i0.wp.com/www.tomosygrapas.com/wp-content/uploads/2025/05/Star-Wars-Banner.jpg?w=999&ssl=1', 'Star Wars decide pasarse al terror', 'Star Wars se pasa al terror con una nueva serie', 'Dark Horse se prepara para darle un nuevo enfoque a Star Wars con una nueva serie de género de terror.');
INSERT INTO News (id, date_new, img_new, text_img, title, description) VALUES (3, '2025-05-05', 'https://i0.wp.com/www.tomosygrapas.com/wp-content/uploads/2025/05/predator-banner.jpg?w=1012&ssl=1', 'Predator se enfrentará a los superhéroes de Marvel', 'Predator se enfrenta a todo el Universo Marvel', 'Marvel Comics anuncia un nuevo crossover donde el icónico Predator de la franquicia cinematográfica, se enfrentará a todo el Universo Marvel.');
ALTER TABLE News ALTER COLUMN id RESTART WITH 4;