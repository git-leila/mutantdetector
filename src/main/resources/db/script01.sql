/* SQL Manager Lite for MySQL                              5.7.2.52112 */
/* ------------------------------------------------------------------- */
/* Host     : localhost                                                */
/* Port     : 3306                                                     */
/* Database : sampledb                                                    */


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'utf8' */;

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `sampledb`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

USE `sampledb`;

/* Structure for the `dna` table : */

CREATE TABLE `dna` (
  `id_dna` INTEGER(11) NOT NULL AUTO_INCREMENT COMMENT 'identificador',
  `secuencia_dna` TEXT COLLATE latin1_swedish_ci NOT NULL,
  `mutant_dna` TINYINT(1) NOT NULL,
  PRIMARY KEY USING BTREE (`id_dna`)
) ENGINE=InnoDB
AUTO_INCREMENT=20 MAX_ROWS=1000000 CHARACTER SET 'latin1' COLLATE 'latin1_swedish_ci'
COMMENT='alamcena las secuencias de dna analizadas'
;

/* Data for the `dna` table  (LIMIT 0,500) */

INSERT INTO `dna` (`id_dna`, `secuencia_dna`, `mutant_dna`) VALUES
  (1,'[TGGCTA, CAGTGC, TTAGGT, ATAAGG, CCTCTA, TCATTG]',1),
  (7,'[ATGCGA, CAGTGC, TTATTT, AGACGG, GCGTCA, TCACTG]',0),
  (8,'[ATGCGAA, ACAGTAC, ATTAAGT, AAGGAGG, ACACCTA, AACACTG, AACACTG]',1),
  (9,'[ATGAGA, CAAGGC, TAGTGT, AGAAGG, CCCCTA, TCACTG]',1),
  (10,'[ATGCGA, CAGTGC, TTATGT, AGAAGG, GCGTCA, TCACTG]',1),
  (11,'[ATGCGA, CAGTGC, TTATTT, AGACGG, GCGTCA, TCACTT]',0),
  (12,'[ATGCGA, CAGTGC, TTATGT, AGAAGG, GCGTCA, TCACTG]',1),
  (13,'[TGCGA, AGTGC, TATGT, GAAGG, CGTCA]',0),
  (14,'[TCAC, GGGA, TACG, ATAT]',0),
  (15,'[TCAC, GGAG, TAAG, ATAT]',0),
  (16,'[TCAC, GGAG, AAAA, ATAT]',1),
  (17,'[TCA, GGG, AAA]',0),
  (18,'[TC, AA]',0),
  (19,'[T]',0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;