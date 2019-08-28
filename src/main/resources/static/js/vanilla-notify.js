const vNotify = (function () {

    const positionOption = {
        topLeft: 'topLeft',
        topRight: 'topRight',
        bottomLeft: 'bottomLeft',
        bottomRight: 'bottomRight',
        center: 'center'
    };

    const options = {
        fadeInDuration: 1000,
        fadeOutDuration: 1000,
        fadeInterval: 50,
        visibleDuration: 5000,
        postHoverVisibleDuration: 500,
        position: positionOption.bottomRight,
        sticky: false,
        showClose: true
    };

    const info = function (params) {
        params.notifyClass = 'vnotify-info';
        return addNotify(params);
    };

    const success = function (params) {
        params.notifyClass = 'vnotify-success';
        return addNotify(params);
    };

    const error = function (params) {
        params.notifyClass = 'vnotify-error';
        return addNotify(params);
    };

    const warning = function (params) {
        params.notifyClass = 'vnotify-warning';
        return addNotify(params);
    };

    const notify = function (params) {
        params.notifyClass = 'vnotify-notify';
        return addNotify(params);
    };

    const custom = function (params) {
        return addNotify(params);
    };

    const addNotify = function (params) {
        if (!params.title && !params.text) {
            return null;
        }

        const frag = document.createDocumentFragment();

        const item = document.createElement('div');
        item.classList.add('vnotify-item');
        item.classList.add(params.notifyClass);
        item.style.opacity = 0;

        item.options = getOptions(params);

        if (params.title) {
            item.appendChild(addTitle(params.title));
        }
        if (params.text) {
            item.appendChild(addText(params.text));
        }
        if (item.options.showClose) {
            item.appendChild(addClose(item));
        }

        item.visibleDuration = item.options.visibleDuration; //option

        const hideNotify = function () {
            item.fadeInterval = fade('out', item.options.fadeOutDuration, item);
        };

        const resetInterval = function () {
            clearTimeout(item.interval);
            clearTimeout(item.fadeInterval);
            item.style.opacity = null;
            item.visibleDuration = item.options.postHoverVisibleDuration;
        };

        const hideTimeout = function () {
            item.interval = setTimeout(hideNotify, item.visibleDuration);
        };

        frag.appendChild(item);
        const container = getNotifyContainer(item.options.position);
        container.appendChild(frag);

        item.addEventListener("mouseover", resetInterval);

        fade('in', item.options.fadeInDuration, item);

        if (!item.options.sticky) {
            item.addEventListener("mouseout", hideTimeout);
            hideTimeout();
        }

        return item;
    };

    const addText = function (text) {
        const item = document.createElement('div');
        item.classList.add('vnotify-text');
        item.innerHTML = text;
        return item;
    };

    const addTitle = function (title) {
        const item = document.createElement('div');
        item.classList.add('vnotify-title');
        item.innerHTML = title;
        return item;
    };

    const addClose = function (parent) {
        const item = document.createElement('span');
        item.classList.add('vn-close');
        item.addEventListener('click', function () {
            remove(parent);
        });
        return item;
    };

    const getNotifyContainer = function (position) {
        const positionClass = getPositionClass(position);
        const container = document.querySelector('.' + positionClass);
        return container ? container : createNotifyContainer(positionClass);
    };

    const createNotifyContainer = function (positionClass) {
        const frag = document.createDocumentFragment();
        container = document.createElement('div');
        container.classList.add('vnotify-container');
        container.classList.add(positionClass);
        container.setAttribute('role', 'alert');

        frag.appendChild(container);
        document.body.appendChild(frag);

        return container;
    };

    const getPositionClass = function (option) {
        switch (option) {
            case positionOption.topLeft:
                return 'vn-top-left';
            case positionOption.bottomRight:
                return 'vn-bottom-right';
            case positionOption.bottomLeft:
                return 'vn-bottom-left';
            case positionOption.center:
                return 'vn-center';
            default:
                return 'vn-top-right';
        }
    };

    const getOptions = function (opts) {
        return {
            fadeInDuration: opts.fadeInDuration || options.fadeInDuration,
            fadeOutDuration: opts.fadeOutDuration || options.fadeOutDuration,
            fadeInterval: opts.fadeInterval || options.fadeInterval,
            visibleDuration: opts.visibleDuration || options.visibleDuration,
            postHoverVisibleDuration: opts.postHoverVisibleDuration || options.postHoverVisibleDuration,
            position: opts.position || options.position,
            sticky: opts.sticky != null ? opts.sticky : options.sticky,
            showClose: opts.showClose != null ? opts.showClose : options.showClose
        };
    };

    const remove = function (item) {
        item.style.display = 'none';
        item.outerHTML = '';
        item = null;
    };

    //New fade - based on http://toddmotto.com/raw-javascript-jquery-style-fadein-fadeout-functions-hugo-giraudel/
    const fade = function (type, ms, el) {
        let isIn = type === 'in',
            opacity = isIn ? 0 : el.style.opacity || 1,
            goal = isIn ? 0.8 : 0,
            gap = options.fadeInterval / ms;

        if (isIn) {
            el.style.display = 'block';
            el.style.opacity = opacity;
        }

        function func() {
            opacity = isIn ? opacity + gap : opacity - gap;
            el.style.opacity = opacity;

            if (opacity <= 0) {
                remove(el);
                checkRemoveContainer();
            }
            if ((!isIn && opacity <= goal) || (isIn && opacity >= goal)) {
                window.clearInterval(fading);
            }
        }

        const fading = window.setInterval(func, options.fadeInterval);
        return fading;
    };

    const checkRemoveContainer = function () {
        var item = document.querySelector('.vnotify-item');
        if (!item) {
            var container = document.querySelectorAll('.vnotify-container');
            for (var i = 0; i < container.length; i++) {
                container[i].outerHTML = '';
                container[i] = null;
            }
        }
    };

    return {
        info: info,
        success: success,
        error: error,
        warning: warning,
        notify: notify,
        custom: custom,
        options: options,
        positionOption: positionOption
    };
})();
