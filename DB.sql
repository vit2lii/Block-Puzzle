DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS rating;
DROP TABLE IF EXISTS score;
DROP SEQUENCE IF EXISTS comment_seq;
DROP SEQUENCE IF EXISTS rating_seq;
DROP SEQUENCE IF EXISTS score_seq;

CREATE TABLE IF NOT EXISTS comment
(
    player      VARCHAR(64) NOT NULL,
    game        VARCHAR(64) NOT NULL,
    comment     VARCHAR(64) NOT NULL,
    commentedOn TIMESTAMP   NOT NULL
);

CREATE TABLE IF NOT EXISTS rating
(
    player  VARCHAR(64) NOT NULL,
    game    VARCHAR(64) NOT NULL,
    rating  INT         NOT NULL,
    ratedOn TIMESTAMP   NOT NULL,
    PRIMARY KEY (player, game)
);

CREATE TABLE IF NOT EXISTS score
(
    player   VARCHAR(64) NOT NULL,
    game     VARCHAR(64) NOT NULL,
    points   INT         NOT NULL,
    playedOn TIMESTAMP   NOT NULL
);