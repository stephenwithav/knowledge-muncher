(ns km.db)

(def ja-hiragana
  {:language "Japanese (Hiragana)"
   :chars
   {:あ "a" :い "i" :う "u" :え "e" :お "o"
    :か "ka" :き "ki" :く "ku" :け "ke" :こ "ko"
    :さ "sa" :し "shi" :す "su" :せ "se" :そ "so"
    :た "ta" :ち "chi" :つ "tsu" :て "te" :と "to"
    :な "na" :に "ni" :ぬ "nu" :ね "ne" :の "no"
    :は "ha" :ひ "hi" :ふ "hu" :へ "he" :ほ "ho"
    :ま "ma" :み "mi" :む "mu" :め "me" :も "mo"
    :ら "ra" :り "ri" :る "ru" :れ "re" :ろ "ro"
    :や "ya" :ゆ "yu" :よ "yo"
    :わ "wa" :を "wo"
    :ん "n"
    :が "ga" :ぎ "gi" :ぐ "gu" :げ "ge" :ご "go"
    :ざ "za" :じ "zi" :ず "zu" :ぜ "ze" :ぞ "zo"
    :だ "da" :ぢ "di" :づ "du" :で "de" :ど "do"
    :ば "ba" :び "bi" :ぶ "bu" :べ "be" :ぼ "bo"
    :ぱ "pa" :ぴ "pi" :ぷ "pu" :ぺ "pe" :ぽ "po"}} )

(def ja-katakana
  {:language "Japanese (Katakana)"
   :chars
   {:ア "a" :イ "i" :ウ "u" :エ "e" :オ "o"
   :カ "ka" :キ "ki" :く "ku" :ケ "ke" :コ "ko"
   :サ "sa" :シ "shi" :ス "su" :セ "se" :ソ "so"
   :タ "ta" :チ "chi" :ツ "tsu" :テ "te" :ト "to"
   :ナ "na" :ニ "ni" :ヌ "nu" :ネ "ne" :ノ "no"
   :ハ "ha" :ヒ "hi" :フ "hu" :ヘ "he" :ホ "ho"
   :マ "ma" :ミ "mi" :ム "mu" :メ "me" :モ "mo"
   :ラ "ra" :リ "ri" :ル "ru" :レ "re" :ロ "ro"
   :ヤ "ya" :ユ "yu" :ヨ "yo"
   :ワ "wa" :ヲ "wo"
   :ン "n"
   :ガ "ga" :ギ "gi" :グ "gu" :ゲ "ge" :ゴ "go"
   :ザ "za" :ジ "zi" :ズ "zu" :ゼ "ze" :ゾ "zo"
   :ダ "da" :ヂ "di" :ヅ "du" :デ "de" :ド "do"
   :バ "ba" :ビ "bi" :ブ "bu" :ベ "be" :ボ "bo"
   :パ "pa" :ピ "pi" :プ "pu" :ペ "pe" :ポ "po"}})

(def all-language-boards
  {:ja-hi ja-hiragana
   :ja-ka ja-katakana})
