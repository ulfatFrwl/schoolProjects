// This will be the object that will contain the Vue attributes
// and be used to initialize it.
let app = {};

// Given an empty app object, initializes it filling its attributes,
// creates a Vue instance, and then initializes the Vue instance.
let init = (app) => {

    // This is the Vue data.
    app.data = {
        posts: [], // See initialization.
    };

    app.index = (a) => {
        // Adds to the posts all the fields on which the UI relies.
        temp = [];      // stores main posts only
        temp_rep = [];  // stores replies only

        for (let p of a) {
            // TODO: Only make the user's own posts editable.
            console.log(user_email);
            if(p.email != user_email)
                {p.editable = false;}
            else
                {p.editable = true;}
            p.edit = false;
            p.is_pending = false;
            p.error = false;
            p.original_content = p.content; // Content before an edit.
            p.server_content = p.content;   // Content on the server.
            if (p.is_reply == null)
                temp.push(p);               // Adding main posts to temp
            else {temp_rep.unshift(p);}
        }

        temp2 = temp;     // Contains final list of posts

        for(i=0;i<temp_rep.length;i++)   // Traversing replies
        {
            for(j=0;j<temp.length;j++)   // For each reply, traversing main posts
            {
                if(temp[j].id == temp_rep[i].is_reply)  // If a matching of ID is found,
                {temp2.splice(j+1,0,temp_rep[i]);}      // add the reply to final list of posts
            }

        }
        return temp2;
    };

    app.reindex = () => {
        // Adds to the posts all the fields on which the UI relies.
        let i = 0;
        for (let p of app.vue.posts) {
            p._idx = i++;
        }
    };

    app.reindex_var = (a) => {
        let i = 0;
        for (let p of a)
            {p._idx = i++;}
    };

    app.do_edit = (post_idx) => {

        // Handler for button that starts the edit.
        // TODO: make sure that no OTHER post is being edited.
        // If so, do nothing.  Otherwise, proceed as below.
        for (let q of app.vue.posts){
            if(q == app.vue.posts[post_idx])  {continue;}
            if(q.edit == true)  {
                if(q.id == null)    {app.vue.posts.splice(q._idx,1);}
                else    q.edit = false;
            }
        }

        let p = app.vue.posts[post_idx];
        console.log(p);
        p.edit = true;
        p.is_pending = false;
        app.reindex();
    };

    app.do_cancel = (post_idx) => {
        // Handler for button that cancels the edit.
        let p = app.vue.posts[post_idx];
        if (p.id === null) {
            // If the post has not been saved yet, we delete it.
            app.vue.posts.splice(post_idx, 1);
            app.reindex();
        } else {
            // We go back to before the edit.
            p.edit = false;
            p.is_pending = false;
            p.content = p.original_content;
        }
    }

    app.do_save = (post_idx) => {
        // Handler for "Save edit" button.
        let p = app.vue.posts[post_idx];
        if (p.content !== p.server_content) {
            p.is_pending = true;
            axios.post(posts_url, {
                content: p.content,
                id: p.id,
                is_reply: p.is_reply,
                author: p.author,
            }).then((result) => {
                console.log("Received:", result.data);
                // TODO: You are receiving the post id (in case it was inserted),
                // and the content.  You need to set both, and to say that
                // the editing has terminated.
                p.edit = false;           // editing has terminated
                p.is_pending = false;     // editing has terminated
                p.id = result.data.id;    // set database ID of p
                p.server_content = result.data.content; // set database content of p
                p.original_content = p.content;         // set original content of p
            }).catch(() => {
                p.is_pending = false;
                console.log("Caught error");
                // We stay in edit mode.
            });
        } else {
            // No need to save.
            p.edit = false;
            p.original_content = p.content;
        }
    }

    app.add_post = () => {
        for (let a of app.vue.posts){
            if(a.edit==true)
            {
                if(a.id == null)    {app.vue.posts.splice(a._idx,1); app.reindex();}
                else    a.edit = false;
            }
        }

        // TODO: this is the new post we are inserting.
        // You need to initialize it properly, completing below, and ...
        let q = {
            id: null,
            edit: true,
            editable: true,
            content: "",
            server_content: null,
            original_content: "",
            author: author_name,
            email: user_email,
            is_reply: null,
        };
        // TODO:
        // ... you need to insert it at the top of the post list.
        app.vue.posts.unshift(q);  // add it to the beginning of posts list
        app.reindex();             // reindex all elements in posts list
    };

    app.reply = (post_idx) => {
        let p = app.vue.posts[post_idx];

        for (let r of app.vue.posts){
            if(r.edit==true)
            {
                if(r.id == null)    {app.vue.posts.splice(r._idx,1);}
                else    r.edit = false;
            }
        }

        if (p.id !== null) {
            // TODO: this is the new reply.  You need to initialize it properly...
            let q = {
                id: null,
                edit: true,
                editable: true,
                content: "",
                server_content: null,
                original_content: "",
                author: author_name,
                email: user_email,
                is_reply: p.id,
            };
            // TODO: and you need to insert it in the right place, and reindex
            // the posts.  Look at the code for app.add_post; it is similar.
            app.vue.posts.splice(post_idx+1,0,q);
            app.reindex();
        }
    };

    app.do_delete = (post_idx) => {
        let p = app.vue.posts[post_idx];
        if (p.id === null) {
            // TODO:
            // If the post has never been added to the server,
            // simply deletes it from the list of posts.
            app.vue.posts.splice(post_idx,1);
        } else {
            // TODO: Deletes it on the server.
            axios.post(delete_url,{
                id: p.id,
            }).then((feedback) =>{
                console.log("Deleted: ", feedback.data);
                app.vue.posts.splice(post_idx,1);
            }).catch(()=> {
                console.log("Caught error");
            });
        }
        app.reindex();
    };

    // We form the dictionary of all methods, so we can assign them
    // to the Vue app in a single blow.
    app.methods = {
        do_edit: app.do_edit,
        do_cancel: app.do_cancel,
        do_save: app.do_save,
        add_post: app.add_post,
        reply: app.reply,
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
        // TODO: Load the posts from the server instead.
        axios.get(posts_url).then(function(response){
            app.vue.posts = app.index(response.data.posts);
            app.reindex();
        })
    };

    // Call to the initializer.
    app.init();
};

// This takes the (empty) app object, and initializes it,
// putting all the code i
init(app);