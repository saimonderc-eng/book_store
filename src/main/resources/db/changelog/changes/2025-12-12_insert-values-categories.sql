--liquibase formatted sql
INSERT INTO categories (ID, NAME, DESCRIPTION)
VALUES (1, 'FANTASY', 'Magical worlds, mythical creatures, and epic adventures'),
       (2, 'FICTION', 'Novels, stories, and literary works of imagination'),
       (3, 'SCIENCE', 'Books on physics, chemistry, biology, and other sciences'),
       (4, 'HISTORY', 'Historical events, biographies, and timelines'),
       (5, 'PHILOSOPHY', 'Works on human thought, ethics, and reasoning'),
       (6, 'PSYCHOLOGY', 'Books about mind, behavior, and mental processes'),
       (7, 'TECHNOLOGY', 'Guides and books on programming, AI, and gadgets'),
       (8, 'ART', 'Books on painting, sculpture, music, and visual arts'),
       (9, 'SELF-HELP', 'Books for personal growth, motivation, and habits'),
       (10, 'TRAVEL', 'Guides, experiences, and tips for exploring the world');