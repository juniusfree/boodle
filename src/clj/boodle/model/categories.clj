(ns boodle.model.categories
  (:require [boodle.services.postgresql :as db]
            [honeysql.core :as hc]
            [honeysql.helpers :as hh]))

(defn select-all
  []
  (db/query {:select [:*] :from [:categories]}))

(defn select-by-id
  [id]
  (db/query {:select [:*] :from [:categories] :where [:= :id id]}))

(defn select-by-name
  [category-name]
  (db/query {:select [:*]
             :from [:categories]
             :where [:= :name category-name]}))

(defn select-categories-with-monthly-expenses
  [from to]
  (-> (hh/select :id_category :category :amount :monthly_budget)
      (hh/from [(-> (hh/select [:c.id :id_category] [:c.name :category]
                               :e.amount :c.monthly_budget)
                    (hh/from [:expenses :e])
                    (hh/join [:categories :c]
                             [:= :e.id_category :c.id])
                    (hh/where [:>= :e.date from] [:<= :e.date to])) :t])
      hc/build
      db/query))

(defn insert!
  [{n :name mb :monthly-budget}]
  (-> (hh/insert-into :categories)
      (hh/columns :name :monthly_budget)
      (hh/values [[n (or mb 0)]])
      db/execute!))

(defn update!
  [{id :id n :name mb :monthly-budget}]
  (-> (hh/update :categories)
      (hh/sset {:name n :monthly_budget (or mb 0)})
      (hh/where [:= :id id])
      db/execute!))

(defn delete!
  [id]
  (-> (hh/delete-from :categories)
      (hh/where [:= :id id])
      db/execute!))
