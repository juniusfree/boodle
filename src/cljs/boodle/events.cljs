(ns boodle.events
  (:require [boodle.ajax :as ajax]
            [boodle.db :as db]
            [re-frame.core :as rf]))

(rf/reg-event-db
 :initialize-db
 (fn [_ _]
   db/default-db))

(rf/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(rf/reg-event-fx
 :bad-response
 (fn [{db :db} [_ response]]
   (.log js/console response)
   {:dispatch [:show-error (get-in response [:response :error])]}))

(rf/reg-event-db
 :modal
 (fn [db [_ data]]
   (assoc db :modal data)))

(rf/reg-event-db
 :show-error
 (fn [db [_ message]]
   (.log js/console message)
   (-> db
       (assoc :show-error true)
       (assoc :error-msg message))))

(rf/reg-event-db
 :close-error
 (fn [db message]
   (-> db
       (assoc :show-error false)
       (dissoc :error-msg message))))

(rf/reg-event-fx
 :validation-error
 (fn [{db :db} [_ response]]
   {:dispatch [:show-validation response]}))

(rf/reg-event-fx
 :modal-validation-error
 (fn [{db :db} [_ response]]
   {:dispatch [:show-modal-validation response]}))

(rf/reg-event-db
 :show-validation
 (fn [db [_ message]]
   (-> db
       (assoc :show-validation true)
       (assoc :validation-msg message))))

(rf/reg-event-db
 :show-modal-validation
 (fn [db [_ message]]
   (-> db
       (assoc :show-modal-validation true)
       (assoc :modal-validation-msg message))))

(rf/reg-event-db
 :close-validation
 (fn [db message]
   (-> db
       (assoc :show-validation false)
       (dissoc :validation-msg message))))

(rf/reg-event-db
 :close-modal-validation
 (fn [db message]
   (-> db
       (assoc :show-modal-validation false)
       (dissoc :modal-validation-msg message))))

(rf/reg-event-db
 :show-menu
 (fn [db [_ _]]
   (-> db
       (update :show-menu not)
       (assoc :show-burger-menu (not (:show-menu db))))))
