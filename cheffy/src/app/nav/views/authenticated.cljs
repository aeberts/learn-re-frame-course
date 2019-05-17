(ns app.nav.views.authenticated
  (:require [app.nav.views.nav-item :refer [nav-item]]
            [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Box]]))

;; Make nav item dispatch an ":active-nav" event
;; - create an "active-nav" event in events.clj
;; - add a function which dispatches a :set-active-nav to each item in nav-items
;; - add an "on-click" handler to the nav-item component which dispatches the event in nav_item.cljs
;; - register the event in our core namespace by adding [app.nav.events] to the core ns require statement
;;
;; - create a subscription called :active-nav to track the active nav in subs.cljs
;; - register the sub in our core namespace by adding [app.nav.subs] to the core ns requre statement
;; - add :active-nav subscription to the "authenticated" component (remember to use '@' to de-reference it!)

(defn authenticated
  []
  (let [active-nav @(rf/subscribe [:active-nav])
        nav-items [{:id :saved
                    :name "Saved"
                    :href "#saved"
                    :dispatch #(rf/dispatch [:set-active-nav :saved])}
                   {:id :recipes
                    :name "Recipes"
                    :href "#recipes"
                    :dispatch #(rf/dispatch [:set-active-nav :recipes])}
                   {:id :inboxes
                    :name "Inbox"
                    :href "#inbox"
                    :dispatch #(rf/dispatch [:set-active-nav :inboxes])}
                   {:id :become-a-chef
                    :name "Chef"
                    :href "#become-a-chef"
                    :dispatch #(rf/dispatch [:set-active-nav :become-a-chef])}
                   {:id :profile
                    :name "Profile"
                    :href "#profile"
                    :dispatch #(rf/dispatch [:set-active-nav :profile])}]]
    [:> Box {:display "flex"
             :justify-content "flex-end"
             :py 1
             :px 2}
     (for [{:keys [id name href dispatch]} nav-items]
       [nav-item {:key id
                  :id id
                  :name name
                  :href href
                  :dispatch dispatch
                  :active-nav active-nav}])]))

(defn foo
  []
  (println "hello"))
