# QuarkFlow
QuarkFlow 是一個打破傳統邊界的開發架構，結合了 Quarkus 的極致效能、Inertia.js 的無縫橋接以及 Svelte 的簡約美學。它將開發者的精力從「繁瑣的溝通協議」中解放出來，投入到真正的「功能創新」賽道上。


## ⚛️ 核心理念：消滅邊界
* **無 API 開發體驗：** 不再需要維護 Swagger 或編寫重複的資料傳輸協議。
* **後端驅動路由：** 利用 Quarkus 控制器直接驅動頁面渲染與 Props 注入。
* **消失的框架：** 發揮 Svelte 輕量化、高性能的編譯時特性，讓 UI 響應如絲般順滑。
* **超音速開發：** Quarkus Dev Mode + Vite HMR，實現全棧即時熱重載。



## 📂 專案結構 (Folder Structure)
QuarkFlow 採用「單一 Repo、雙引擎」的佈局，確保前後端邏輯緊密結合，同時保持開發工具鏈的獨立性。

```text
QuarkFlow/
├── src/
│   ├── main/
│   │   ├── java/org/quarkflow/          # 🚀 Quarkus 後端核心
│   │   │   ├── controllers/             # Inertia 控制器 (Render 邏輯)
│   │   │   ├── entities/                # Hibernate Panache 資料模型
│   │   │   └── inertia/                 # Inertia 配置與過濾器 (Middleware)
│   │   └── resources/
│   │       ├── application.properties    # Quarkus 配置文件
│   │       └── web/                     # 🎨 Svelte 前端核心 (由 Vite 驅動)
│   │           ├── src/
│   │           │   ├── app.js           # Inertia 進入點 (Setup)
│   │           │   ├── Pages/           # Svelte 頁面組件 (對應後端路由)
│   │           │   └── Shared/          # 共用組件 (Layouts, UI Components)
│   │           ├── public/              # 靜態資源
│   │           ├── package.json         # 前端依賴
│   │           └── vite.config.js       # Vite 配置 (輸出至 META-INF/resources)
├── target/                              # 編譯產出
├── pom.xml                              # Maven 依賴管理 (包含前端插件)
└── README.md
```




## 🛠️ 快速上手

1. 環境準備
    * JDK 17+
    * Node.js 18+
    * Maven 3.8+

2. 啟動開發模式
    在根目錄執行以下指令，Quarkus 將自動處理後端邏輯，並透過 Vite 監控前端變更：
    
    ```bash
    ./mvnw quarkus:dev
    ```


3. 範例：建立你的第一個 Flow
後端 (Java)

```java
@Path("/projects")
public class ProjectController {
    @GET
    public Response index() {
        // 直接渲染 Svelte 組件並傳遞資料
        return Inertia.render("ProjectList", Map.of("projects", projectService.listAll()));
    }
}
```

### 前端 (Svelte)

```html
<!-- src/main/resources/web/src/Pages/ProjectList.svelte -->
<script>
  export let projects; // 自動從後端接收的資料
</script>

<h1>My Projects</h1>
<ul>
  {#each projects as p}
    <li>{p.name}</li>
  {/each}
</ul>
```

---


## 🚀 為什麼選擇 QuarkFlow？
在傳統架構中，約 50% 的時間 消耗在 API 定義與狀態同步。QuarkFlow 透過 Inertia 徹底抹平了這層隔閡，讓資料像「夸克」粒子一樣自由流動，實現真正的 「想到即做到」。
