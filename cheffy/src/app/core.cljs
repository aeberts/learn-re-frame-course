(ns app.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.db]
            ;; -- auth ---
            [app.auth.views.log-in :refer [log-in]]
            [app.auth.views.profile :refer [profile]]
            [app.auth.views.sign-up :refer [sign-up]]
            ;; -- become a chef --
            [app.become-a-chef.views.become-a-chef :refer [become-a-chef]]
            ;; -- inboxes --
            [app.inbox.views.inboxes :refer [inboxes]]
            ;; -- nav ---
            [app.nav.views.nav :refer [nav]]
            [app.nav.events]
            [app.nav.subs]
            ;; -- recipes --
            [app.recipes.views.recipes :refer [recipes]]
            [app.theme :refer [cheffy-theme]]
            ["@smooth-ui/core-sc" :refer [Normalize ThemeProvider Button Grid Row Col]]))

(defn pages
  [page-name]
  (case page-name
    :profile [profile]
    :sign-up [sign-up]
    :log-in [log-in]
    :become-a-chef [become-a-chef]
    :inboxes [inboxes]
    :recipes [recipes]
    [recipes]))

(defn app
  []
  (let [active-nav @(rf/subscribe [:active-nav])]
  ;; ":<>" is a Reagent fragment - it allows you to avoid having to put
  ;; things in divs (Reagent only allows you to return one element from a component)
  ;;
    [:<>
     [:> Normalize]
     [:> ThemeProvider {:theme cheffy-theme}]
     [:> Grid {:fluid false}
      [:> Row
       [:> Col
        [nav]
        [pages active-nav]]]]]))

(defn ^:dev/after-load start
  []
  (r/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (rf/dispatch-sync [:initialize-db])
  (start))
