(ns boodle.common
  (:require
   [boodle.i18n :refer [translate]]
   [cljs.pprint :as pp]
   [re-frame.core :as rf])
  (:require-macros [better-cond.core :refer [defnc]]))

(defn navbar-burger
  [active]
  (if active
    :a.navbar-burger.is-active
    :a.navbar-burger))

(defn navbar-menu
  [active]
  (if active
    :div.navbar-menu.is-active
    :div.navbar-menu))

(defn header
  []
  (fn []
    (let [show-menu @(rf/subscribe [:show-menu])
          show-burger-menu @(rf/subscribe [:show-burger-menu])]
      [:div.container
       [:nav.navbar.is-transparent
        [:div.navbar-brand
         [:div.navbar-item
          [:h2.title.is-2 (translate :it :header/boodle)]]
         [(navbar-burger show-burger-menu)
          {:role "button"
           :on-click #(rf/dispatch [:show-menu])}
          [:span {:aria-hidden :true}]
          [:span {:aria-hidden :true}]
          [:span {:aria-hidden :true}]]]
        [(navbar-menu show-menu)
         [:div.navbar-start
          [:a.navbar-item {:href "/"}
           (translate :it :header/expenses)]
          [:a.navbar-item {:href "/savings"}
           (translate :it :header/savings)]
          [:a.navbar-item {:href "/categories"}
           (translate :it :header/categories)]]]]])))

(defn footer
  []
  (fn []
    [:footer.footer
     [:div.content.has-text-centered
      [:p "Developed with "
       [:i.fa.fa-heart]
       " by "
       [:a {:href "https://www.manueluberti.eu"}
        "Manuel Uberti"]]]]))

(defn page-title
  [title]
  (fn []
    [:h3.title.is-3.has-text-centered
     title]))

(defn render-option
  [item]
  [:option
   {:key (random-uuid)
    :value (:id item)}
   (:name item)])

(defnc format-number
  [n]
  (nil? n) "0"
  (= n 0) "0"
  (-> (pp/cl-format nil "~,2f" n)
               (clojure.string/replace #"\." ",")))

(defnc format-neg-or-pos
  [n]
  (zero? n) "0"
  (pos? n) (str "+" (format-number n))
  (format-number n))
