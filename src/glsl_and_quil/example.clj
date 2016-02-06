(ns glsl-and-quil.example
  (:require [quil.core :as q :include-macros true]))

(defn setup []
  (q/no-loop))

(defn draw []
  (let [w    (float (q/width))
        h    (float (q/height))
        x    (float (q/mouse-x))
        y    (float (q/mouse-y))
        t    (float (/ (q/millis) 1000.0))
        frag (q/load-shader "example.frag")]
    ; Set all the uniforms in the shader
    (doto frag
      (.set "u_resolution" w h)
      (.set "u_mouse" x y)
      (.set "u_time" t))
    ; Set the shader
    (q/shader frag)
    ; Turn off the border for the rectangle
    (q/no-stroke)
    ; Draw the rectangle that will employ the shader
    (q/rect 0 0 w h)))

(q/defsketch test-shader
  :title    "test-shader"
  :setup    setup
  :renderer :p3d
  :draw     draw
  :size     [800 800])
