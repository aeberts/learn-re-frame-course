(ns app.core
  (:require [reagent.core :as r]
            [app.theme :refer [cheffy-theme]]
            ["@smooth-ui/core-sc" :refer [Normalize ThemeProvider Button]]))

(defn app
  []
  ;; Reagent only allows one component to be returned from a fn
  ;; To get around this restriction we can wrap everything in a [:div]
  ;; However, this is tedious and reduces the legibility of our code.
  ;; To get around this we can use a React fragment: :<>
  ;; It allows us to avoid having to wrap everything in divs
  ;;
  [:<>
   ;; Using native React classes directly from hiccup is not supported.
   ;; To get around this problem we can pass the component to
   ;; reagent / adapt-react-class which will render it properly, e.g:
   ;;
   ;; [(r/adapt-react-class Normalize)]
   ;; [(r/adapt-react-class Button) "Hello"]
   ;;
   ;; However, this is tedious to type and makes the code less readable
   ;; To make this easier we can use the the Reagent ":>" feature like so:
   [:> Normalize]
   [:> ThemeProvider {:theme cheffy-theme}
    ;; smooth-ui properties can be passed as maps with keywords in place of strings:
    [:> Button "Hello"]]])

(defn ^:dev/after-load start
  []
  (r/render [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
