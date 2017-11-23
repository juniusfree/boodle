(ns boodle.model.aims
  (:require [boodle.services.postgresql :as db]))

(defn select-all
  []
  (db/query ["SELECT * FROM aims"]))

(defn select-by-id
  [id]
  (db/query ["SELECT * FROM aims WHERE id = cast(? as integer)" id]))

(defn select-by-name
  [aim-name]
  (db/query ["SELECT * FROM aims WHERE name = ?" aim-name]))

(defn select-active
  []
  (db/query ["SELECT * FROM aims WHERE archived = false"]))

(defn select-archived
  []
  (db/query ["SELECT * FROM aims WHERE archived = true"]))

(defn select-aims-with-transactions
  []
  (db/query ["SELECT a.id, a.name as aim, a.target, t.amount
              FROM transactions t
              INNER JOIN aims a ON t.id_aim = a.id"]))

(defn insert!
  [aim]
  (let [{:keys [name target archived]} aim]
    (db/update! ["INSERT INTO aims(name, target, archived)
                  VALUES (?, cast(? as double precision), ?)"
                 name target archived])))

(defn update!
  [aim]
  (let [{:keys [id name target archived]} aim]
    (db/update! ["UPDATE aims SET name = ?, target = ?, archived = ?
                  WHERE id = cast(? as integer)"
                 name target archived id])))

(defn delete!
  [id]
  (db/delete! ["DELETE FROM aims WHERE id = cast(? as integer)" id]))