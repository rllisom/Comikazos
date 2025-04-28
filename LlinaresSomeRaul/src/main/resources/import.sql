insert into Category (id, name) values (1, 'DC');
insert into Category (id, name) values (2, 'Marvel');
insert into Category (id, name) values (3, 'Independiente');

-- INSERT INTO Comic (id, name, desc, price, sales, pages, review, date, url, category_id) 
-- VALUES (NEXT VALUE FOR comic_seq, 
-- 'Biblioteca Marvel Omnibus. La Imposible Patrulla-X 3', 
-- '¡Una histórica edición de la totalmente nueva y totalmente diferente Patrulla-X como nunca 
-- habías imaginado! El tratamiento de contenidos de Biblioteca Marvel continúa en la línea Marvel Omnibus, a través de este gigantesco volumen. Hito mutante,
-- tras hito mutante, empezando por ''La saga de El Nido'', continuando por la metáfora definitiva de la intolerancia hacia los diferentes que supuso ''Dios ama,
-- el hombre mata'', con la miniserie de Lobezno que reunió a Chris Claremont y Frank Miller, la integración de Pícara en La Patrulla-X, la llegada de Los Morlocks 
-- y el regreso de Fénix Oscura, en la saga que presenta además a Madelyne Pryor, entre otras muchas historias imprescindibles.',
-- 33.27, 705, 100, 2.36, '2025-04-05', 'https://www.panini.es/media/catalog/product/cache/2d16730310b7945c46ddd1ca513e3c42/s/o/somni046_0.jpg', 2);

INSERT INTO Comic (id, name, desc, price, sales, pages, review, date, url, category_id) VALUES (NEXT VALUE FOR comic_seq, 'Superman Absolute', 'El héroe más brillante y puro del Universo DC, sin su fortaleza, sin una familia, sin un hogar… ¡El Hombre de Acero en su versión Absolute! Solo y aislado, será aquí donde comprendas la profunda importancia que tiene para los héroes el entorno y la comunidad que los esculpe.', 54.03, 459, 100, 2.86, '2024-04-27', 'https://www.panini.es/media/catalog/product/cache/2d16730310b7945c46ddd1ca513e3c42/s/a/sabsu001_0.jpg', 1);
