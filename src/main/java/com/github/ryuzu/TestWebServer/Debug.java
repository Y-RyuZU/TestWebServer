package com.github.ryuzu.TestWebServer;

import com.github.ryuzu.TestWebServer.Utilities.DataBaseUtilities;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class Debug {
    @RequestMapping("/api/debug/export")
    public static String put(@RequestParam("page") int i) {
        List<String> pages = new ArrayList<>();
        String debug = """
                        記号化
                        =伝えたいことを言葉や絵で表現
                        •メディア
                        =相手に記号を届ける媒体
                        •復号
                        =視覚や聴覚で記号を読み取る
                        •電子書籍のファイルフォーマットとして広く使われてる規格は？
                        =EPUB
                        •特定の発信者から不特定多数の受信者に情報を届けるメディアは？
                        =マスメディア
                        •アナログデータの特徴は？
                        =連続量のため無限個のデータ
                        •デジタルデータの特徴は？
                        =離散値のためデータ数は有限
                        •ある一定のルールに従って情報を0と1に変換することをなんというか？
                        =符号化
                        •1ビットは何バイトか？
                        =8バイト
                        •1KBは何バイトか？
                        =1024バイト
                        •10の何乗かを表す接頭語を何というか？
                        =SI接頭語
                        •コンピュータにおいて0と1のみを用いる表現形式を何というか？また、人間が読み書きできる以外のデータを何データというか？
                        =バイナリ　/  バイナリデータ
                        •左端のビットが正負を表しているとき特に何ビットというか？
                        =符号ビット
                        •コンピュータにおいてはハードウェアやソフトウェアの速度や性能を表す基準を何というか
                        =ベンチマーク
                        •コンピュータ内部で数値や文字や記号を二進法で表現したものを何と呼ぶか？
                        =文字コード
                        •記号を8ビットで表現するコード体系を何というか？
                        =JIS8ビットコード
                        •記号を7ビットで表現するコード体系は？
                        =ASCII（アスキーでも可）
                        •前者と後者で、片仮名を表現できるのはどちらか？
                        =前者（JIS8ビットコード）
                        •日本語の全角文字を表現できるように日本で開発されたコード体系は？
                        =シフトJISコード
                        •多言語を共通して利用できるように開発されたコードは？
                        =ユニコード
                        •使用するコンピュータによって文字化けすることがある文字を何文字というか？
                        =機種依存文字
                        •JISを日本語の正式名称で答えなさい。またどんな規格か？
                        =日本産業規格　／　形状、寸法、品質などについて国が定めた規格
                        •音の3要素は？
                        =振幅、周波数、波形
                        •音のデジタル化の手順を順番に３つ答えよ
                        =標本化／量子化／符号化
                        •1秒間に標本化する回数をなんというか？
                        =標本化周波数
                        •電圧の段階値に割り振るビット数を何というか？
                        =量子化ビット数
                        •これを何変換というか？
                        =D／A変換
                        •音質を良くするとデータ量が多くなりますが逆にデータ量を減らすと音質が悪くなります。このような状態を何というか？
                        =トレードオフ
                        •音のテンポや音階などの情報を楽譜のように記録する方法を何というか？
                        =MIDI
                        •カメラで撮った写真のデジタル化を順番に３つ答えよ
                        =標本化／量子化／符号化
                        •1インチあたりのドット数を何というか？また、それの単位は？
                        =解像度／dpi
                        •色を量子化で分ける時の段階値を何というか？
                        =階調
                        •一般にフルカラーは何ビットか？
                        =24ビット
                        •動画を構成する一枚一枚の写真をなんというか？
                        =フレーム
                        •1秒間に表示する枚数を何というか？
                        =フレームレート
                        •単位は？
                        =fps
                        •光の三原色は？
                        =赤、青、緑
                        •圧縮とは何か？
                        =一定の手順に従ってデータ量を減らす技術
                        •戻せるやつは？
                        =可逆圧縮
                        •戻せないやつは？
                        =不可逆圧縮
                        •繰り返し現れる構造を省略する圧縮を何圧縮というか？
                        =ランレングス圧縮
                        •このうち不可逆圧縮を選べ　JPEG GIF PNG
                        =JPEG
                        •出現頻度が高いデータに短い符号を、低いデータに長い符号を割り当てる圧縮を何というか
                        =ハフマン符号
                        •人間が聞き取れない高音は何Hzから？
                        =16000Hz
                        •デジタルデータのプラス面を4つ答えよ
                        =多様な形態の情報を統合できる、情報が劣化しない、情報の加工が容易、管理しやすい
                        •異なる形式の情報を統合して扱う技術や機器は？
                        =マルチメディア
                        •OCRの役割は？
                        =文字を文字情報に変換（デジタル化）
                        •デジタルデータのマイナス面を4つ
                        =デジタル化で微妙な情報が失われる、著作者の権利が侵害される、情報が流出する、データ量が膨大になる
                        •サーバーなどの機器を集約して設置し効率的に運用する施設は？
                        =データセンター
                        •世界初のネット
                        =WWW
                        •高速かつ大容量通信の回線
                        =ブロードバンド
                        •多人数の知識を蓄積して活用可能にしたもの
                        =集合知
                        •パケット交換方式の始祖
                        =アーパネット
                """;

        return write(debug);
    }

    public static String write(String csv) {
        try {
            FileWriter fw = new FileWriter("技術2学期一対一.csv", false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            pw.print("問題");
            pw.print(",");
            pw.print("答え");
            pw.println();

            String[] sets = csv.split("•");
            for(String set : sets) {
                String question = set.split("=")[0];
                String answer = set.split("=")[1];
                pw.print(question);
                pw.print(",");
                pw.print(answer);
                pw.println();
            }
            pw.close();

            return fw.getEncoding();

        } catch (IOException ex) {
        }
        return null;
    }
}
