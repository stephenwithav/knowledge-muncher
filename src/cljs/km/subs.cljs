(ns km.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :new-game
 (fn [db v]
   (select-keys db [:cells :seek :language])))
