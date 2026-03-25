<script>
  let { children } = $props();
</script>

<div class="layout">
  <nav>
    <a href="/">QuarkFlow</a>
  </nav>
  <main>
    {@render children()}
  </main>
</div>

<style>
  .layout {
    min-height: 100vh;
    font-family: system-ui, -apple-system, sans-serif;
  }
  nav {
    padding: 1rem 2rem;
    border-bottom: 1px solid #eee;
  }
  nav a {
    font-weight: 700;
    font-size: 1.25rem;
    color: #4361ee;
    text-decoration: none;
  }
  main {
    padding: 2rem;
  }
</style>
