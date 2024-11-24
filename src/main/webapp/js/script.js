document.addEventListener("DOMContentLoaded", function() {
	const movieGrid = document.getElementById("movieGrid");

	// Sample data for movies (in a real app, you would retrieve this from a server)
	const movies = [
		{
			title: "The Dark Knight",
			genre: "Action",
			image: "./img/4.jpg",
			id: 4
		},
		{
			title: "Inception",
			genre: "Sci-Fi",
			image: "./img/2.jpg",
			id: 2
		},
		{
			title: "Parasite",
			genre: "Thriller",
			image: "./img/1.jpg",
			id: 1
		}
		,
		{
			title: "Spider-Man: Across the Spider-Verse (2023)",
			genre: "Sci-fi",
			image: "./img/5.jpg",
			id: 5
		},
		{
			title: "Mission: Impossible - Dead Reckoning Part One",
			genre: "Action",
			image: "./img/3.jpeg",
			id: 3
		},
		{
			title: "Avengers: Endgame",
			genre: "Sci-fi",
			image: "./img/6.jpg",
			id: 6
		}
	];

	// Function to render movies in the grid
	function renderMovies() {
		movies.forEach(movie => {
			const movieCard = document.createElement("div");
			movieCard.classList.add("movie-card");

			movieCard.innerHTML = `
                <img src="${movie.image}" alt="${movie.title}">
                <h3>${movie.title}</h3>
                <p class="genre">${movie.genre}</p>
            `;
			movieGrid.appendChild(movieCard);
		});
	}

	renderMovies();
});



