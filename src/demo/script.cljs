(comment
  (ns demo.script)
  (defn main [& cli-args]
    (prn "hello world")))

(ns
    demo.script
    (:require
     [helix.core :refer [defnc $]]
     [helix.hooks :as hooks]
     [helix.dom :as d]
     ["react-dom" :as rdom]))

;; define components using the `defnc` macro
(defnc greeting
  "A component which greets a user."
  [{:keys [name]}]
  ;; use helix.dom to create DOM elements
  (d/div "Hello, " (d/strong name) "!"))

(defnc app []
  (let [[state set-state] (hooks/use-state {:name "Helix User"})]
    (d/div
     (d/h1 "Welcome!")
      ;; create elements out of components
      ($ greeting {:name (:name state)})
      (d/input {:value (:name state)
                :on-change #(set-state assoc :name (.. % -target -value))}))))

;; start your app with your favorite React renderer
(defn init [] (rdom/render ($ app) (js/document.getElementById "app")))


(defn main [& cli-args]
  (prn "hello world")
  (init))