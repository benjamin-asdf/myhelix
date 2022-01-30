(ns
    demo.script
    (:require
     ["ink" :as ink]
     ["ink-fuzzy-select" :default FuzzySelect]
     [helix.core :refer [defnc $]]
     [helix.hooks :as hooks]
     [helix.dom :as d]))

(comment
  (ns demo.script)
  (defn main [& cli-args]
    (prn "hello world")))

(def items (clj->js
            [{:label "First"
              :value "first"}
             {:label "Second"
              :value "second"}]))


(defnc app []
  (let [[state set-state] (hooks/use-state {:name "Helix User"})]
    ($
     ink/Box
     ($ ink/Text "Welcome! " (:name state)))))

(defnc
  fuzzy-query
  []
  (let [[select set-select] (hooks/use-state "")]
    ($
     ink/Box
     {:flex-direction "column"}
     ($ FuzzySelect {:options items :on-select set-select})
     ($ ink/Text select))))

;; start your app with your favorite React renderer

(defn
  main
  [& cli-args]
  (prn "hello world")
  (ink/render ($ main)))
