// This will be the object that will contain the Vue attributes
// and be used to initialize it.
let app = {};

// Given an empty app object, initializes it filling its attributes,
// creates a Vue instance, and then initializes the Vue instance.
let init = (app) => {

    // This is the Vue data.
    app.data = {
        notes: [],
        rate: null,
    };

    // Adds to the notes all UI fields
    app.index = (a) => {
        temp1 = []; // store starred notes
        temp2 = []; // store regular notes
        for (let p of a) {
            p.editable = true;
            p.edit_title = false;
            p.edit_content = false;

            p.server_title = p.title;
            p.server_content = p.content;
            p.server_colour = p.colour;
            if(p.rating == null)
                p.rating = 0;
            if(p.rating == 1)
                temp1.push(p);
            else
                temp2.push(p);
            p.server_rating = p.rating;
        }

        finalList = temp1.concat(temp2);
        app.vue.rate = temp1.length;

        return finalList;
    };

    app.reindex = () => {
        let i = 0;
        for (let p of app.vue.notes){
            p._idx = i++;
        }
    };

    app.do_cancel = (notes_idx) => {
        let p = app.vue.notes[notes_idx];
        p.edit = false;
        p.content = p.original_content;
    };

    app.do_save = (notes_idx) => {
        let p = app.vue.notes[notes_idx];

        if(p.title !== p.server_title || p.content !== p.server_content || p.colour !== p.server_colour || p.rating !== p.server_rating){
            axios.post(notes_url, {
                id: p.id,
                title: p.title,
                content: p.content,
                colour: p.colour,
                rating: p.rating,
            }).then((result)=>{
                console.log("Received: ", result.data);
                p.id = result.data.id;
                p.server_title = result.data.title;
                p.server_content = result.data.content;
                p.server_colour = result.data.colour;
                p.server_rating = result.data.rating;

                app.vue.notes.splice(notes_idx, 1);
                app.reindex();

                if(p.rating == 0){  // If this note is not starred
                    if(app.vue.rate == 0) // no starred notes in list
                        app.vue.notes.splice(0,0,p);

                    else  // there are starred notes
                        app.vue.notes.splice(app.vue.rate,0,p);
                }

                else // this note is starred
                    app.vue.notes.splice(0,0,p);

                app.reindex();
            })
        }
    };

    app.do_rate = (notes_idx) => {
        let p = app.vue.notes[notes_idx];
        if(p.rating == 0 || p.rating == null){
            p.rating = 1;
            app.vue.rate++;
        }
        else {
            p.rating = 0;
            app.vue.rate--;
        }

        app.do_save(notes_idx);
    };

    app.do_change_colour = (notes_idx, colour_idx) => {
        let p = app.vue.notes[notes_idx];
        p.colour = colour_idx;

        app.do_save(notes_idx);
    };

    app.add_note = () => {
        let q = {
            id: null,
            edit_title: false,
            edit_content: false,
            editable: true,

            title: "Title",
            content: "Content",
            colour: 4,      // default colour is pink
            rating: 0,      // no rating

            server_title: null,

            server_content: null,

            server_colour: null,

            server_rating: null,
        };
        app.vue.notes.unshift(q);
        app.reindex();
    };

    app.do_delete = (notes_idx) => {
        let p = app.vue.notes[notes_idx];
        if(p.id == null){
            app.vue.notes.splice(notes_idx,1);
        } else{
            axios.post(delete_url, {
                id: p.id,
            }).then((feedback) => {
                console.log("Deleted: ", feedback.data);
                app.vue.notes.splice(notes_idx,1);
            }).catch(()=> {
                console.log("Caught error");
            });
        }
        app.reindex();
    };

    // We form the dictionary of all methods, so we can assign them
    // to the Vue app in a single blow.
    app.methods = {
        do_cancel: app.do_cancel,
        do_save: app.do_save,
        do_rate: app.do_rate,
        do_change_colour: app.do_change_colour,
        add_note: app.add_note,
        do_delete: app.do_delete,
    };

    // This creates the Vue instance.
    app.vue = new Vue({
        el: "#vue-target",
        data: app.data,
        methods: app.methods
    });

    // And this initializes it.
    app.init = () => {
        axios.get(notes_url).then(function(response){
            app.vue.notes = app.index(response.data.notes);
            app.reindex();
        })
    };

    // Call to the initializer.
    app.init();
};

// This takes the (empty) app object, and initializes it,
// putting all the code i
init(app);
