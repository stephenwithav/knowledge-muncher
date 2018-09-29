(ns km.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :new-game
 (fn [db v]
   (select-keys db [:levels-to-go :language])))


(re-frame/reg-sub
 :next-level
 (fn [db v]
   (select-keys db [:current-level-native :current-level-foreign :current-board :language])))
