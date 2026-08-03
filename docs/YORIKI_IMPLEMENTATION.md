# 妖力実装記録

## 目的

Botaniaのマナのように、ブロック間で物を動かすための動力源として妖力を追加する。今回の対象は次の5ブロックである。

- クリエイティブ妖力プール
- 妖力送信機
- 妖力受信機
- 妖力かまど
- 妖力接続ケーブル

## ブランチ

`docs/GUIDELINES.md` の `feat/〇〇` 形式に従い、`feat/yoryoku` ブランチで実装する。

## 実装方法

NeoForge 21.1.247 の標準 `IEnergyStorage` と `Capabilities.EnergyStorage.BLOCK` を妖力の接続APIとして利用する。独自APIを増やさず、他の機械からも同じCapability経由で接続できる構成にした。

### 妖力の仕様

| 機能 | 容量 | 入力 | 出力 |
| --- | ---: | ---: | ---: |
| クリエイティブ妖力プール | 無限 | 0/t | 64/t |
| 妖力送信機 | 256 | 64/t | 64/t |
| 妖力受信機 | 256 | 64/t | 64/t |
| 妖力かまど | 256 | 16/t | なし |
| 妖力接続ケーブル | 256 | 64/t | 64/t |

送信機と受信機は、正面から出力し背面から入力する。向きは設置時のプレイヤーの向きで決まる。送信機の正面は赤、背面は緑、受信機の正面は青、背面は黄色で表示する。妖力接続ケーブルは全方向から入出力できる。

妖力の流れは、例えば次のように接続する。

`クリエイティブ妖力プール → 妖力送信機 → 妖力接続ケーブル → 妖力受信機 → 妖力かまど`

送信処理は1ブロックあたり1 tick の合計64妖力を上限とする。妖力かまどは通常の溶鉱レシピを使い、燃料スロットを使わず、処理中だけ毎tick 16妖力を消費する。ケーブルは複数本を隣接させて延長できる。

### クラス構成

- `yoriki/YorikiEnergyStorage.java`: 容量・入出力上限付きの妖力ストレージ。
- `yoriki/YorikiTransfer.java`: 隣接ブロックへ妖力を送る処理。
- `register/YorikiBlockRegister.java`: 5ブロックとBlockItemの登録。
- `register/YorikiBlockEntityRegister.java`: BlockEntityTypeの登録。
- `register/YorikiCapabilityRegister.java`: NeoForge Energy capabilityの登録。
- `yoriki/block/entity/*`: 妖力プール、送信機、受信機、かまど、ケーブルの状態とtick処理。
- `assets/` と `data/`: BlockState、モデル、翻訳、ドロップ定義。

## 実装手順

1. ガイドラインとGit状態を確認する。
2. `feat/yoryoku` ブランチを作成する。
3. 妖力ストレージ、転送処理、Capability登録を追加する。
4. 5種類のブロック、BlockEntity、BlockItemを登録する。
5. BlockState・モデル・翻訳・ドロップを追加する。
6. `./gradlew compileJava`、`./gradlew test`、`./gradlew build` を実行する。

## 変更ログ

### 2026-08-04

- 妖力の容量・転送量・かまど消費量の定数を追加。
- 無限供給のクリエイティブ妖力プールを追加。
- 256妖力を保持し、64妖力/tで送受信する送信機・受信機を追加。
- 16妖力/tを消費する溶鉱レシピ対応の妖力かまどを追加。
- 送信機・受信機の入力面/出力面を色分けしたモデルへ変更。
- 送信機と受信機の間を中継する妖力接続ケーブルを追加。
- 5ブロックの登録、Capability、BlockEntity、モデル、翻訳、ドロップ定義を追加。
- 実装時のAI利用範囲は、NeoForge APIに合わせた一メソッド単位のコード補助と、実装手順・変更記録の文章化に限定した。

### 追加ファイル

- `yoriki/`: 妖力定数、ストレージ、Capability経由の転送処理。
- `yoriki/block/`: 妖力プール、送信機、受信機、かまど、ケーブルのBlock実装。
- `yoriki/block/entity/`: 5ブロックのBlockEntity実装。
- `register/YorikiBlockRegister.java`: ブロックとBlockItemの登録。
- `register/YorikiBlockEntityRegister.java`: BlockEntityTypeの登録。
- `register/YorikiCapabilityRegister.java`: Energy capabilityの登録。
- `assets/creepyspooky/blockstates/`: 5ブロックの状態定義。
- `assets/creepyspooky/models/`: ブロック・アイテムモデル。
- `data/creepyspooky/loot_tables/blocks/`: ブロックドロップ定義。
- `docs/YORIKI_IMPLEMENTATION.md`: 実装方法、手順、変更ログ。

## 検証

- `./gradlew compileJava`: 成功。
- `./gradlew test`: 再実行予定。
- `./gradlew build`: 再実行予定。
