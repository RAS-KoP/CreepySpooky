# 妖力実装記録

## 目的

Botaniaのマナのように、ブロック間で物を動かすための動力源として妖力を追加する。今回の対象は次の5ブロックと接続用アイテムである。

- クリエイティブ妖力プール
- 妖力送信機
- 妖力受信機
- 妖力かまど
- 妖力接続ケーブル
- 妖力接続の杖

## ブランチ

`docs/GUIDELINES.md` の `feat/〇〇` 形式に従い、`feat/yoryoku_AI` ブランチで実装する。

## 実装方法

NeoForge 21.1.247 の標準 `IEnergyStorage` と `Capabilities.EnergyStorage.BLOCK` を妖力の接続APIとして利用する。独自APIを増やさず、他の機械からも同じCapability経由で接続できる構成にした。

### 妖力の仕様

| 機能 | 容量 | 入力 | 出力 |
| --- | ---: | ---: | ---: |
| クリエイティブ妖力プール | 無限 | 0/t | 64/t |
| 妖力送信機 | 256 | 64/t | 無線64/t |
| 妖力受信機 | 256 | 無線64/t | 正面64/t |
| 妖力かまど | 256 | 16/t | なし |
| 妖力接続ケーブル | 256 | 64/t | 64/t |

送信機は背面から妖力を受け取り、登録した受信機へ無線送信する。受信機は登録元から妖力を受け取り、正面へ出力する。送信機の正面は赤（無線送信）、背面は緑（入力）、受信機の正面は青（出力）、背面は金色（無線入力）で表示する。妖力接続ケーブルは全方向から入出力できる。

妖力の流れは、例えば次のようになる。

`クリエイティブ妖力プール → 妖力送信機 ==無線==> 妖力受信機 → 妖力かまど`

送信処理は1接続あたり1 tick の合計64妖力を上限とする。妖力かまどは通常の溶鉱レシピを使い、燃料スロットを使わず、処理中だけ毎tick 16妖力を消費する。ケーブルは複数本を隣接させて延長でき、無線接続を使わない構成でも利用できる。

### 無線接続の使い方

1. 妖力接続の杖を持ち、妖力送信機を右クリックして登録対象として選択する。
2. 同じディメンションにある妖力受信機を右クリックして接続を登録する。
3. クリエイティブ妖力プールを送信機の背面に隣接させ、受信機の正面に妖力かまどなどの機械を置く。
4. 送信機または受信機を杖でShift+右クリックすると、その接続を解除する。

接続先の座標とディメンションはBlockEntityのNBTへ保存するため、ワールド再起動後も維持される。杖で再登録した場合、送信機と受信機の既存接続は解除してから新しい接続へ更新する。同じディメンションの送信機・受信機だけを接続できる。

### クラス構成

- `yoriki/YorikiEnergyStorage.java`: 容量・入出力上限付きの妖力ストレージ。
- `yoriki/YorikiTransfer.java`: 隣接ブロックへ妖力を送る処理。
- `register/YorikiBlockRegister.java`: 5ブロック、BlockItem、接続の杖の登録。
- `register/YorikiBlockEntityRegister.java`: BlockEntityTypeの登録。
- `register/YorikiCapabilityRegister.java`: NeoForge Energy capabilityの登録。
- `yoriki/block/entity/*`: 妖力プール、送信機、受信機、かまど、ケーブルの状態とtick処理。
- `yoriki/item/YorikiLinkingWandItem.java`: 送信機・受信機の無線接続登録と解除。
- `assets/` と `data/`: BlockState、モデル、翻訳、ドロップ定義。

## 実装手順

1. ガイドラインとGit状態を確認する。
2. `feat/yoryoku_AI` ブランチを作成する。
3. 妖力ストレージ、転送処理、Capability登録を追加する。
4. 5種類のブロック、BlockEntity、BlockItemを登録する。
5. BlockState・モデル・翻訳・ドロップ・テクスチャを追加する。
6. 接続の杖とBlockEntityのNBT保存を追加する。
7. `./gradlew compileJava`、`./gradlew test`、`./gradlew build` を実行する。

## 変更ログ

### 2026-08-04

- 妖力の容量・転送量・かまど消費量の定数を追加。
- 無限供給のクリエイティブ妖力プールを追加。
- 256妖力を保持し、64妖力/tで送受信する送信機・受信機を追加。
- 16妖力/tを消費する溶鉱レシピ対応の妖力かまどを追加。
- 送信機・受信機の入力面/出力面を色分けしたモデルへ変更。
- 送信機と受信機の間を中継する妖力接続ケーブルを追加。
- 送信機を入力専用、受信機を正面出力専用へ整理し、無線転送へ対応。
- 送信機・受信機を登録する妖力接続の杖と、接続情報のNBT保存を追加。
- 送信機・受信機・ケーブル・接続の杖のテクスチャを追加。
- 5ブロックの登録、Capability、BlockEntity、モデル、翻訳、ドロップ定義を追加。
- 実装時のAI利用範囲は、NeoForge APIに合わせた一メソッド単位のコード補助と、実装手順・変更記録の文章化に限定した。

### 追加ファイル

- `yoriki/`: 妖力定数、ストレージ、Capability経由の転送処理。
- `yoriki/block/`: 妖力プール、送信機、受信機、かまど、ケーブルのBlock実装。
- `yoriki/block/entity/`: 5ブロックのBlockEntity実装。
- `yoriki/item/YorikiLinkingWandItem.java`: 無線接続の登録・解除。
- `register/YorikiBlockRegister.java`: ブロックとBlockItemの登録。
- `register/YorikiBlockEntityRegister.java`: BlockEntityTypeの登録。
- `register/YorikiCapabilityRegister.java`: Energy capabilityの登録。
- `assets/creepyspooky/blockstates/`: 5ブロックの状態定義。
- `assets/creepyspooky/models/`: ブロック・アイテムモデル。
- `assets/creepyspooky/textures/`: 妖力の入出力方向と接続を示すテクスチャ。
- `data/creepyspooky/loot_tables/blocks/`: ブロックドロップ定義。
- `docs/YORIKI_IMPLEMENTATION.md`: 実装方法、手順、変更ログ。

## 検証

- `./gradlew compileJava`: 成功。
- `./gradlew test`: 成功（テストソースなし）。
- `./gradlew build`: 成功。
