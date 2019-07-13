(ns app.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.db]
            ;; -- nav ---
            [app.nav.views.nav :refer [nav]]
            [app.nav.events]
            [app.nav.subs]
            [app.theme :refer [cheffy-theme]]
            ["@smooth-ui/core-sc" :refer [Normalize ThemeProvider Button]]))

(defn app
  []
  ;; ":<>" is a Reagent fragment - it allows you to avoid having to put
  ;; things in divs (Reagent only allows you to return one element from a component)
  [:<>
   [:> Normalize]
   [:> ThemeProvider {:theme cheffy-theme}
    [nav]]])

(defn ^:dev/after-load start
  []
  (rf/dispatch-sync [:initialize-db])
  (r/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
