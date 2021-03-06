(ns boodle.api.funds
  (:require
   [boodle.model.funds :as funds]
   [boodle.utils :as utils]
   [compojure.core :refer [context defroutes DELETE GET POST PUT]]
   [ring.util.http-response :as response]))

(defn find-all
  [request]
  (let [ds (:datasource request)
        funds (funds/select-all ds)
        total (apply + (map :amount funds))]
    (-> {}
        (assoc :funds funds)
        (assoc :total total))))

(defn find-by-id
  [request id]
  (let [ds (:datasource request)
        id (utils/str->integer id)]
    (funds/select-by-id ds id)))

(defn insert!
  [request]
  (let [ds (:datasource request)
        req (utils/request-body->map request)
        record (-> req
                   (assoc :amount 0)
                   (utils/record-str->date :date))]
    (funds/insert! ds record)))

(defn update!
  [request]
  (let [ds (:datasource request)
        req (utils/request-body->map request)
        record (utils/record-str->double req :amount)]
    (funds/update! ds record)))

(defn delete!
  [request id]
  (let [ds (:datasource request)
        id (utils/str->integer id)]
    (funds/delete! ds id)))

(defroutes routes
  (context "/api/fund" [id]
    (GET "/find" request
      (response/ok (find-all request)))
    (GET "/find/:id" [id :as request]
      (response/ok (find-by-id request id)))
    (POST "/insert" request
      (response/ok (insert! request)))
    (PUT "/update" request
      (response/ok (update! request)))
    (DELETE "/delete/:id" [id :as request]
      (response/ok (delete! request id)))))
