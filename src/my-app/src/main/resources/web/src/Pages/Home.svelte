<script>
  let { greeting } = $props();
</script>

<svelte:head>
  <title>QuarkFlow - Home</title>
</svelte:head>

<main>
  <h1>{greeting}</h1>
  <p>Welcome to <strong>QuarkFlow</strong> — powered by Quarkus + Inertia.js + Svelte 5.</p>
</main>

<style>
  main {
    max-width: 640px;
    margin: 4rem auto;
    font-family: system-ui, -apple-system, sans-serif;
    text-align: center;
  }
  h1 {
    color: #4361ee;
    font-size: 2.5rem;
  }
  p {
    color: #555;
    font-size: 1.2rem;
    line-height: 1.6;
  }
</style>
